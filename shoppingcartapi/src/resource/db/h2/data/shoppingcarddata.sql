MERGE INTO PRODUCT as t
USING (SELECT 0 as ID, 'apple' as NAME, 1.0 as PRICE) as s
on (t.NAME = s.NAME)
WHEN NOT MATCHED THEN
INSERT (NAME, PRICE) VALUES (s.NAME, s.PRICE)
;

MERGE INTO PRODUCT as t
USING (SELECT 0 as ID, 'orange' as NAME, 1.1 as PRICE) as s
on (t.NAME = s.NAME)
WHEN NOT MATCHED THEN
INSERT (NAME, PRICE) VALUES (s.NAME, s.PRICE)
;

MERGE INTO PRODUCT as t
USING (SELECT 0 as ID, 'banana' as NAME, 1.2 as PRICE) as s
on (t.NAME = s.NAME)
WHEN NOT MATCHED THEN
INSERT (NAME, PRICE) VALUES (s.NAME, s.PRICE)
;

MERGE INTO PRODUCT as t
USING (SELECT 0 as ID, 'pineapple' as NAME, 1.3 as PRICE) as s
on (t.NAME = s.NAME)
WHEN NOT MATCHED THEN
INSERT (NAME, PRICE) VALUES (s.NAME, s.PRICE)
;

MERGE INTO PRODUCT as t
USING (SELECT 0 as ID, 'pear' as NAME, 1.4 as PRICE) as s
on (t.NAME = s.NAME)
WHEN NOT MATCHED THEN
INSERT (NAME, PRICE) VALUES (s.NAME, s.PRICE)
;

MERGE INTO PRODUCT as t
USING (SELECT 0 as ID, 'cherry' as NAME, 1.5 as PRICE) as s
on (t.NAME = s.NAME)
WHEN NOT MATCHED THEN
INSERT (NAME, PRICE) VALUES (s.NAME, s.PRICE)
;
