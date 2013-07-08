# --- !Ups
DROP TABLE Comment;

# --- !Downs
CREATE TABLE Comment(
	content LONGTEXT,
	date DATETIME,
	book_id BIGINT(20),
u	ser_id BIGINT(20)
);