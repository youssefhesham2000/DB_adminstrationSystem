USE ORDER_PROCESSING_SYSTEM;

DROP TRIGGER IF EXISTS CONFIRM_ORDER;
delimiter |
CREATE TRIGGER CONFIRM_ORDER BEFORE DELETE ON LIBRARY_ORDER_DETAILS
  FOR EACH ROW
  BEGIN
    UPDATE BOOK_COPIES SET inStock = inStock + OLD.quantity WHERE ISBN = OLD.ISBN;
  END;
|
delimiter ;

DROP TRIGGER IF EXISTS PLACE_ORDER;
delimiter |
CREATE TRIGGER PLACE_ORDER AFTER UPDATE ON BOOK_COPIES
  FOR EACH ROW
  BEGIN
    IF NEW.inStock < NEW.threshold THEN
		INSERT INTO LIBRARY_ORDER_DETAILS(ISBN, quantity, requestTime) VALUES (NEW.ISBN, NEW.threshold, NOW());
	END IF;
  END;
|
delimiter ;

DROP TRIGGER IF EXISTS CHECK_VALID_SELL;
delimiter |
CREATE TRIGGER CHECK_VALID_SELL BEFORE UPDATE ON BOOK_COPIES
  FOR EACH ROW
  BEGIN
    IF NEW.inStock < 0 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'quantity cannot be negative';
	END IF;
  END;
|
delimiter ;
