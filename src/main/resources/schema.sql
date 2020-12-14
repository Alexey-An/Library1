CREATE TABLE Author (
    authorId INT PRIMARY KEY,
    authorName VARCHAR(255) NULL
);

CREATE TABLE Book (
    bookId INT PRIMARY KEY,
    bookTitle VARCHAR(255) NULL
);

CREATE TABLE Author_Book (
    authorId INT,
    bookId INT
);