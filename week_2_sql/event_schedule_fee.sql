DROP EVENT IF EXISTS UPDATEEVENT;
CREATE EVENT IF NOT EXISTS UPDATEEVENT
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP

ON COMPLETION PRESERVE

DO
	UPDATE PATRON
JOIN loans
ON loans.patron_id = patron.id
set patron.balance = .25 * (

SELECT datediff(CURRENT_DATE(), loans.loan_due_date)


) 
WHERE datediff(CURRENT_DATE(), loans.loan_due_date) >= 1;




UPDATE loans
SET loans.status = 1
WHERE datediff(CURRENT_DATE(), loans.loan_due_date) >= 1;



UPDATE PATRON
set patron_status = 1
WHERE patron.id =(
	SELECT patron_id
    FROM loans
    WHERE status = 1
);


