# --- !Ups
ALTER TABLE User_Book
  RENAME TO ReadBooks;
  
CREATE TABLE GoingToReadBooks(
user_id BIGINT(20) NOT NULL,
going_read_book_id BIGINT(20) NOT NULL,
);


# --- !Downs
ALTER TABLE ReadBooks
  RENAME TO User_book;
  
DROP TABLE GoingToReadBooks;