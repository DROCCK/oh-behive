-- # Complete Users
INSERT INTO address(id, street, apt, city, state, zip) VALUES (1, '3878 Shawn Way', '#153', 'Loomis', 'CA', '95663');
INSERT INTO contact_info(id, email, phone) VALUES (1, 'me@me.com', '(916) 704-4718');
INSERT INTO person (id, name) VALUES (1, 'Esther Andrews Lytton');
INSERT INTO user (id, username, password) VALUES (1, 'user@user.com', '$2a$10$KST6QYRGfS7U3AkVMua56OchNPEM4X/1env/h8hTEVjtdvNWIrG1e');

-- # Owners
INSERT INTO owner(id, person_id) VALUES (1, 1);

-- # Regions
INSERT INTO region(id, name) VALUES (1, 'Cali');
INSERT INTO region(id, name) VALUES (2, 'Dakotas');

-- # Yards
INSERT INTO yard(id, yard_name, status, combo, max_hives, owner_id, rent_receiver_id, address_id, singles, doubles, longitude, latitude, region_id) VALUES (1, 'Andrews', 'IN USE', '1234', '80', 1, 1, 1, 5, 5, -121.30267710000001, 38.5991552, 1);

-- # Orchards
INSERT INTO orchard(yard_id, hive_count) VALUES (1, 25);

-- # PolliInspection
INSERT INTO polli_inspection(id, orchard, inspect_date, purpose) VALUES (1, 1, '2012-09-17', 'INSPECT');
INSERT INTO polli_inspection(id, orchard, inspect_date, purpose) VALUES (2, 1, '2012-10-17', 'FEED');

-- # Contracts
-- # INSERT INTO contract(id, move_in_date, move_out_date, amount, broker_id, orchard_id) VALUES (1, '2016/04/01', '2016/08/01', 527, 1, 1);
INSERT INTO contract(id, move_in_date, move_out_date, amount, broker_id, orchard_id) VALUES (1, '2012-09-17', '2012-10-17', 527, 1, 1);

-- # Roles
INSERT INTO role(id, name) VALUES (1, 'ADMIN');
INSERT INTO role(id, name) VALUES (2, 'USER');

-- # Users_Roles
INSERT INTO users_roles(user_id, role_id) VALUES (1, 1);
