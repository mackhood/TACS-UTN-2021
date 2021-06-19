INSERT INTO role(id, name) values(1, "ADMIN"),(2, "PLAYER")

INSERT INTO user(id, email, password, username) values(1, "admin@gmail.com", "$2y$12$qR9ULVnvGS1GGyFuxzpSwOHn5c.6cSs886N2hCkWaohBVipY2vm4q", "admin")
INSERT INTO user(id, email, password, username) values(2, "player@gmail.com", "$2y$12$qz1eN7va7AlTC.lxyyHDb.s9yQgi/27zCayxbLVv72DcTGfeyNyA.", "player")
INSERT INTO user(id, email, password, username) values(3, "hola@gmail.com", "$2y$12$qz1eN7va7AlTC.lxyyHDb.s9yQgi/27zCayxbLVv72DcTGfeyNyA.", "player3")


INSERT INTO attribute(id, name) values(1,"strength"),(2,"intelligence"),(3, "speed"),(4, "durability"),(5, "power"),(6, "combat")

INSERT INTO user_role(user_id, role_id) values(1, 1),(2,2)
