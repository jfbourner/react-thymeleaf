INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');
--INSERT INTO roles (id, name) VALUES (3, 'ADMIN');
--INSERT INTO roles (id, name) VALUES (4, 'USER');


INSERT INTO user_account ( FIRSTNAME, LASTNAME , USERNAME   , EMAIL               ,PASSWORD                                                              ,ENABLED) VALUES
                         ( 'Jack'   , 'Bourner', 'jfbourner','jfbourner@gmail.com','{bcrypt}$2a$12$qK45W4GSD7vOj9KhJY7lretIIxySjYTY7VsDnWPPPnbr7fFw78TIe',true   ),
                         ( 'User'   , 'One'    , 'user'     ,'user@gmail.com'     ,'{bcrypt}$2a$12$qK45W4GSD7vOj9KhJY7lretIIxySjYTY7VsDnWPPPnbr7fFw78TIe',true   );
INSERT INTO users_roles(user_id,role_id ) VALUES
                       (1      ,2       ),
                       (1      ,1       ),
                     --  (1      ,3       ),
                       (2      ,1       );
                      -- (2      ,4       );
