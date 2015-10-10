# Complete Users
INSERT INTO user (id, username, password) VALUES (0, 'user@user.com', 'password');
INSERT INTO person (id, name) VALUES (0, 'Esther Andrews Lytton');
INSERT INTO contact_Info(id, email, phone) VALUES (0, '', '(916) 704-4718');
INSERT INTO address(id, street, apt, city, state, zip) VALUES (0, '3878 Shawn Way', '#153', 'Loomis', 'CA', '95663');

# Roles
INSERT INTO role(id, name) VALUES (0, 'ADMIN');
INSERT INTO role(id, name) VALUES (1, 'USER');

# Users_Roles
INSERT INTO users_roles(user_id, role_id) VALUES (0, 0);

# Yards
INSERT INTO yard (yard_name, status, combo, max_hives, owner_id, rent_receiver_id, address_id) VALUES ('Andrews', 'In Use', '1234', '80', '1', '1', '1');