{\rtf1\ansi\ansicpg1252\cocoartf1347\cocoasubrtf570
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
\paperw11900\paperh16840\margl1440\margr1440\vieww17420\viewh8720\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural

\f0\fs24 \cf0 insert into company (name, joinDate) values ('testName', CURRENT_TIMESTAMP());\
insert into company (name, joinDate) values ('testName2\'92, CURRENT_TIMESTAMP());\
\
insert into team (companyId, name, formedDate) values (1, 'team1', CURRENT_TIMESTAMP())\
insert into team (companyId, name, formedDate) values (1, 'team2\'92, CURRENT_TIMESTAMP())\
\
\
INSERT INTO employee (companyId, teamId, name, daysOff, specialDaysOff, joinDate, endDate) VALUES (1, 1, 'emp1\'92, 5, 6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());\
INSERT INTO employee (companyId, teamId, name, daysOff, specialDaysOff, joinDate, endDate) VALUES (1, 1, 'emp2\'92, 6, 7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());\
INSERT INTO employee (companyId, teamId, name, daysOff, specialDaysOff, joinDate, endDate) VALUES (1, 1, 'emp3', 6, 7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());\
\
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural
\cf0 INSERT INTO employee (companyId, teamId, name, daysOff, specialDaysOff, joinDate, endDate) VALUES (1, 2, 'emp11\'92, 5, 6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());\
INSERT INTO employee (companyId, teamId, name, daysOff, specialDaysOff, joinDate, endDate) VALUES (1, 2, 'emp22\'92, 7, 8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());\
INSERT INTO employee (companyId, teamId, name, daysOff, specialDaysOff, joinDate, endDate) VALUES (1, 2, 'emp33\'92, 9, 10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());\
\
INSERT INTO daysOff (companyId, employeeId, day) VALUES (1, 1, CURRENT_TIMESTAMP());\
INSERT INTO daysOff (companyId, employeeId, day) VALUES (1, 1, CURRENT_TIMESTAMP());\
INSERT INTO daysOff (companyId, employeeId, day) VALUES (1, 1, CURRENT_TIMESTAMP());\
\
INSERT INTO daysOff (companyId, employeeId, day) VALUES (1, 2, CURRENT_TIMESTAMP());\
\
\
INSERT INTO specialDaysOff (companyId, employeeId, day) VALUES (1, 1, CURRENT_TIMESTAMP());\
INSERT INTO specialDaysOff (companyId, employeeId, day) VALUES (1, 1, CURRENT_TIMESTAMP());\
INSERT INTO specialDaysOff (companyId, employeeId, day) VALUES (1, 1, CURRENT_TIMESTAMP());\
\
INSERT INTO specialDaysOff (companyId, employeeId, day) VALUES (1, 2, CURRENT_TIMESTAMP());\
\
\
INSERT INTO task (companyId, teamId, code, name, description, totalHours, startDate, endDate) VALUES (1, 1, 'T1', 'Task1', 'Description1', 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());\
INSERT INTO task (companyId, teamId, code, name, description, totalHours, startDate, endDate) VALUES (1, 1, \'92T2\'92, 'Task2\'92, 'Description2\'92, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());\
INSERT INTO task (companyId, teamId, code, name, description, totalHours, startDate, endDate) VALUES (1, 1, 'T3\'92, 'Task3\'92, 'Description3\'92, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());\
INSERT INTO task (companyId, teamId, code, name, description, totalHours, startDate, endDate) VALUES (1, 1, 'T4\'92, 'Task4\'92, 'Description4\'92, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());\
\
\
INSERT INTO taskAssociations (companyId, employeeId, taskId) VALUES (1, 1, 1);\
INSERT INTO taskAssociations (companyId, employeeId, taskId) VALUES (1, 1, 2);\
INSERT INTO taskAssociations (companyId, employeeId, taskId) VALUES (1, 1, 3);\
\
\
INSERT INTO requests (companyId, employeeId, type, description, requestDate) VALUES (1, 3, 'type1', 'desc1', CURRENT_TIMESTAMP());\
INSERT INTO requests (companyId, employeeId, type, description, requestDate) VALUES (1, 3, 'type2', 'desc3', CURRENT_TIMESTAMP());\
INSERT INTO requests (companyId, employeeId, type, description, requestDate) VALUES (1, 3, 'type3', 'desc3', CURRENT_TIMESTAMP());\
\
\
INSERT INTO workedHours (companyId, employeeId, taskId, date, hours) VALUES (1, 1, 1, CURRENT_TIMESTAMP(), 3);\
INSERT INTO workedHours (companyId, employeeId, taskId, date, hours) VALUES (1, 1, 2, CURRENT_TIMESTAMP(), 4);\
}