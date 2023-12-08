create table part(
    id INT auto_increment NOT NULL PRIMARY KEY,
    name VARCHAR (100),
    quantity INT
);
CREATE TABLE machine(
    id INT auto_increment NOT NULL PRIMARY KEY,
    name VARCHAR (100),
    quantity INT
);
CREATE TABLE subassembly(
    id INT auto_increment NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    quantity INT
);

CREATE TABLE machineSubassembly(
    id INT auto_increment NOT NULL PRIMARY KEY,
    machineId INT,
    FOREIGN KEY (machineId) REFERENCES machine(id),
    subassemblyId INT,
    FOREIGN KEY (subassemblyId) REFERENCES subassembly(id)
);

CREATE TABLE PartSubassembly(
    id INT auto_increment NOT NULL PRIMARY KEY,
    partId INT,
    FOREIGN KEY (partId) REFERENCES part(id),
    subassemblyId INT,
    FOREIGN KEY (subassemblyId) REFERENCES subassembly(id)
);

CREATE TABLE customer(
    id INT auto_increment NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE address(
    id INT auto_increment NOT NULL PRIMARY KEY,
    streetName VARCHAR(100) NOT NULL,
    number INT NOT NULL,
    apartment VARCHAR(10),
    zipcode INT NOT NULL,
    city VARCHAR,
    country VARCHAR
);

CREATE TABLE customerAddress(
    customerId INT NOT NULL,
    FOREIGN KEY (customerId) REFERENCES customer(id),
    addressID INT,
    FOREIGN KEY (addressID) REFERENCES address(id)
);

CREATE TABLE orderContent(
    id INT auto_increment NOT NULL PRIMARY KEY,
    orderID INT NOT NULL,
    FOREIGN KEY (orderID) REFERENCES orders(id),
    machineId INT,
    FOREIGN KEY (machineId) REFERENCES machine(id),
    partId INT,
    FOREIGN KEY (partId) REFERENCES part(id)
);

CREATE TABLE orders(
    id INT auto_increment NOT NULL PRIMARY KEY,
    customerId INT NOT NULL,
    FOREIGN KEY (customerId) REFERENCES customer(id),
    orderContentId INT,
    FOREIGN KEY (orderContentId) REFERENCES orderContent(id)
);