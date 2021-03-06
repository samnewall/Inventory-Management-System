create database Inventory_Management_System;
use Inventory_Management_System;

create table items (
	ID int auto_increment not null,
    itemDescription varchar(255),
    unitPrice decimal(4,2), # max value of £9999.99
    quantity int,
    totalValue decimal(4,2), # max value of £9999.99
    primary key(ID)
);


create table transactions (
	ID int auto_increment not null,
    itemDescription varchar(255),
    quantitySold int,
    quantityBrought int,
    amount decimal(4,2), # max value of £9999.99
    currentStock int,
    dateAdded int, # date format = DDMMYYYY (8 ints long)
    primary key(ID)
);

# Autogenerate an item id for each new row starting from 10001 for both tables
alter table items auto_increment = 10000;
alter table transactions auto_increment = 10000;

# Change root default password to a more secure, complex password
alter user 'root'@'localhost' identified by 'KiloSierraFoxtrot1!';

