USE ORDER_PROCESSING_SYSTEM;


delimiter |
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
    DROP TABLE USER_CART;
    COMMIT;
  END;
|
delimiter ;
