CREATE PROCEDURE purchaseCart(IN USER_ID INT)

-- TODO: check credit details

-- add each cart item to sales
  BEGIN
	SET autocommit = 0;
    START TRANSACTION;
    -- get cart items of user
    CREATE TEMPORARY TABLE USER_CART(ISBN VARCHAR(17), quantity INT, sellingPrice DOUBLE);
    INSERT INTO USER_CART
    SELECT ISBN, quantity FROM 
    CART C INNER JOIN BOOK B ON S.ISBN=B.ISBN
    WHERE userID=USER_ID ;
    -- decrement the quantity of books
    UPDATE BOOK_COPIES B
    INNER JOIN USER_CART C ON B.ISBN=C.ISBN
    SET B.inStock=B.inStock-C.QUANTITY;
    -- add each cart item to sales
    INSERT INTO BOOK_SALES(userID, totalPrice, quantity, date, ISBN)
    SELECT USER_ID, quantity*sellingPrice, quantity, NOW(), ISBN FROM USER_CART;
    DELETE FROM CART as C WHERE C.userID = USER_ID;
    DROP TABLE USER_CART;
    COMMIT;
  END;
|
delimiter ;



CREATE DEFINER=SAMPLE@% PROCEDURE AddBook(ISBN varchar(17) ,publisher_name varchar(45),title varchar(45),publication_year year,selling_price INT,category varchar(45),threshold int , authors varchar(45))
BEGIN
    declare publisher_id int;
    set publisher_id = getPublisher(publisher_name);
    SET autocommit = 0;
    start transaction;
        insert into book VALUES (ISBN,title,publication_year,selling_price,category);
        insert into book_copies values (ISBN,threshold+10,threshold);
    COMMIT;
END