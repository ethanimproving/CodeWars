package com.leetcode.heapandpriority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * @see <a href="https://leetcode.com/problems/design-twitter/">Design Twitter</a>
 */
public class DesignTwitter {

    @Getter
    @ToString
    class User {
        private int uid;
        private Set<Integer> followingIDs;
        private List<Integer> tweetIDs;

        public User(int uid) {
            this.uid = uid;
            this.followingIDs = new HashSet<>();
            this.followingIDs.add(this.uid); // by default, follows self
            this.tweetIDs = new ArrayList<>();
        }

        // Most recent ones are at the end
        public void follow(int fid) {
            this.followingIDs.add(fid);
        }

        public void unfollow(int fid) {
            this.followingIDs.remove(fid);
        }

        // If remove tweet existed, treeSet would've been used (to maintain ordering)
        public void postTweet(int tweetID) {
            this.tweetIDs.add(tweetID);
        }
    }

    @AllArgsConstructor
    @Getter
    @ToString
    class Tweet {
        private int tweetID;
        private int timestamp;
    }

    class Database {
        private static int GLOBAL_TIME = 0;

        // 1 <= userID <= 500 ---> Can be 501 sized array instead of HashMap
        private static int NUM_USERS = 501;
        private User[] users;
        private boolean[] userExists;

        // 0 <= tweetID <= 10^4 --> Can be 10^4 + 1 sized array instead of HashMap
        private static int NUM_TWEETS = 10000 + 1;
        private Tweet[] tweets;

        // Acts like a 'node' to indicate where to go next
        @AllArgsConstructor
        class Triplet {
            int userId;
            int index;
            int tweetID;
        }

        // Compare the linked list of tweets present in the user class
        // Nested class to access the 'tweets'
        class TweetComparator implements Comparator<Triplet> {
            @Override
            public int compare(Triplet o1, Triplet o2) {
                // Compare max. with respect to timestamp
                return (
                        tweets[o2.tweetID].getTimestamp() -
                                tweets[o1.tweetID].getTimestamp()
                );
            }
        }

        // From outside, Database.TweetComparator obj = databaseObj.new TweetComparator()
        private final TweetComparator comparator;

        // Constructor
        public Database() {
            this.users = new User[NUM_USERS];
            this.userExists = new boolean[NUM_USERS];
            Arrays.fill(this.userExists, false);

            this.tweets = new Tweet[NUM_TWEETS];
            this.comparator = new TweetComparator();
        }

        private void createUserMaybe(int uid) {
            if (this.userExists[uid])
                return;

            this.userExists[uid] = true;
            User user = new User(uid);
            this.users[uid] = user;
        }

        private void createTweet(int tweetId) {
            Tweet tweet = new Tweet(tweetId, Database.GLOBAL_TIME);
            Database.GLOBAL_TIME++;
            this.tweets[tweetId] = tweet;
        }

        public void postTweet(int uid, int tweetId) {
            // Create new User if doesn't exist
            createUserMaybe(uid);

            // Create new Tweet
            createTweet(tweetId);

            // Post the tweet
            this.users[uid].postTweet(tweetId);
        }

        public void follow(int followerId, int followeeId) {
            // Create users if doesn't exist
            createUserMaybe(followerId);
            createUserMaybe(followeeId);

            // Follow
            this.users[followerId].follow(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            // Create users if doesn't exist
            createUserMaybe(followerId);
            createUserMaybe(followeeId);

            // Unfollow
            this.users[followerId].unfollow(followeeId);
        }

        private List<Tweet> getMaxTopK(int userId, int k) {
            // Check if this user exists or not
            if (!this.userExists[userId])
                return new ArrayList<>();

            // Use top k of sorted list type logic
            PriorityQueue<Triplet> pq = new PriorityQueue<>(this.comparator);

            User user = this.users[userId];
            Set<Integer> followingIDs = user.getFollowingIDs();

            // Take the most recent (i.e. last) tweet for each of the following user
            for (int followingID : followingIDs) {
                User followingUser = this.users[followingID];
                List<Integer> followingUserTweetIds = followingUser.getTweetIDs();

                if (followingUserTweetIds.isEmpty())
                    continue;

                int lastIndex = followingUserTweetIds.size() - 1;
                int mostRecentTweetID = followingUserTweetIds.get(lastIndex);
                Triplet triplet = new Triplet(followingID, lastIndex, mostRecentTweetID);

                pq.add(triplet);
            }
            // Now, remove from pq, and "move required pointers/links" accordingly
            List<Tweet> maxTweets = new ArrayList<>();

            while (!pq.isEmpty() && (maxTweets.size() < k)) {
                Triplet top = pq.remove();
                maxTweets.add(tweets[top.tweetID]);

                // For this top's user, if 'lesser' recent tweet is available, add to pq
                int userID = top.userId;
                int nextIndex = top.index - 1; // move backwards due to ArrayList
                if (nextIndex < 0)
                    continue;

                int newTweetID = users[userID].getTweetIDs().get(nextIndex);
                Triplet newTriplet = new Triplet(userID, nextIndex, newTweetID);
                pq.add(newTriplet);
            }

            return maxTweets;
        }

        public List<Integer> getMaxTop10TweetIDs(int userId) {
            List<Tweet> tweets = getMaxTopK(userId, 10);
            // Instead of streams, normal for loop may also be used
            return tweets
                    .stream()
                    .map(Tweet::getTweetID)
                    .collect(Collectors.toList());
        }
    }

    class Twitter {
        private Database db;

        public Twitter() {
            this.db = new Database();
        }

        public void postTweet(int userId, int tweetId) {
            this.db.postTweet(userId, tweetId);
        }

        public List<Integer> getNewsFeed(int userId) {
            return db.getMaxTop10TweetIDs(userId);
        }

        public void follow(int followerId, int followeeId) {
            this.db.follow(followerId, followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            this.db.unfollow(followerId, followeeId);
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
