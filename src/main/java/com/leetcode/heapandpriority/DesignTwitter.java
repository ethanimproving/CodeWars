package com.leetcode.heapandpriority;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * @see <a href="https://leetcode.com/problems/design-twitter/">Design Twitter</a>
 */
public class DesignTwitter {

    class Twitter {

        Map<Integer, Set<Integer>> followerIdToFolloweeIds;
        List<Integer> postUser; // TweetId is the index and userId of the poster is the value.
        List<Integer> post; // Total posts with tweetIds at the value.

        public Twitter() {
            followerIdToFolloweeIds = new HashMap<>();
            postUser = new ArrayList<>();
            post = new ArrayList<>();
        }

        public void postTweet(int userId, int tweetId) {
            postUser.add(userId);
            post.add(tweetId);
        }

        public List<Integer> getNewsFeed(int userId) {
            var list = new ArrayList<Integer>();
            var followeeIds = followerIdToFolloweeIds.get(userId);
            int postsToBeReturned = 0, tweetIds = post.size() - 1;
            while (postsToBeReturned < 10 && tweetIds >= 0) {
                if (postUser.get(tweetIds) == userId || (followeeIds != null && followeeIds.contains(postUser.get(tweetIds)))) {
                    list.add(post.get(tweetIds));
                    postsToBeReturned++;
                }
                tweetIds--;
            }
            return list;
        }

        public void follow(int followerId, int followeeId) {
            followerIdToFolloweeIds.computeIfAbsent(followerId, k -> new HashSet<>());
            followerIdToFolloweeIds.get(followerId).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (followerIdToFolloweeIds.get(followerId) != null)
                followerIdToFolloweeIds.get(followerId).remove(followeeId);
        }
    }

    @Test
    void demo() {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
        assertIterableEquals(List.of(5), twitter.getNewsFeed(1)); // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
        twitter.follow(1, 2); // User 1 follows user 2.
        twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
        assertIterableEquals(List.of(6, 5), twitter.getNewsFeed(1)); // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        twitter.unfollow(1, 2); // User 1 unfollows user 2.
        assertIterableEquals(List.of(5), twitter.getNewsFeed(1)); // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
    }
}
