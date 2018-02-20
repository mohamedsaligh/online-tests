DROP TABLE updates;
DROP TABLE friends;
DROP TABLE users;

-- users table where it mains the user profiles
CREATE TABLE users (
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255),
    friend_count INTEGER DEFAULT 1,
PRIMARY KEY (username));

-- friends table maintains the connection between friends
CREATE TABLE friends (
    friend_one VARCHAR(255) NOT NULL,
    friend_two VARCHAR(255) NOT NULL,
    status VARCHAR(1) DEFAULT '0',
PRIMARY KEY (friend_one,friend_two),
FOREIGN KEY (friend_one) REFERENCES users(username),
FOREIGN KEY (friend_two) REFERENCES users(username));

-- updates or posts of a user
CREATE TABLE updates (
    update_id VARCHAR(255) NOT NULL,
    update VARCHAR(2000),
    username VARCHAR(45),
    created_dt DATE,
    ipaddress VARCHAR(45),
PRIMARY KEY (update_id),
FOREIGN KEY (username) REFERENCES users(username));
