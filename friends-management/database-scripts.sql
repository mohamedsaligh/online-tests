-- users table where it mains the user profiles
CREATE TABLE users (
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100),
    friend_count INTEGER,
PRIMARY KEY (username));

-- friends table maintains the connection between friends
CREATE TABLE friends (
    friend_one VARCHAR(100) NOT NULL,
    friend_two VARCHAR(100) NOT NULL,
    status VARCHAR(1) DEFAULT '0',
PRIMARY KEY (friend_one,friend_two),
FOREIGN KEY (friend_one) REFERENCES users(username),
FOREIGN KEY (friend_two) REFERENCES users(username));
