insert into company (name, joinDate) values ('testName', CURRENT_TIMESTAMP());
insert into company (name, joinDate) values ('testName2', CURRENT_TIMESTAMP());

insert into team (companyId, name, formedDate) values (1, 'team1', CURRENT_TIMESTAMP());
insert into team (companyId, name, formedDate) values (1, 'team2', CURRENT_TIMESTAMP());


INSERT INTO employee (companyId, teamId, name, daysOff, specialDaysOff, joinDate, endDate) VALUES (1, 1, 'emp1', 5, 6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO employee (companyId, teamId, name, daysOff, specialDaysOff, joinDate, endDate) VALUES (1, 1, 'emp2', 6, 7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO employee (companyId, teamId, name, daysOff, specialDaysOff, joinDate, endDate) VALUES (1, 1, 'emp3', 6, 7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO employee (companyId, teamId, name, daysOff, specialDaysOff, joinDate, endDate) VALUES (1, 2, 'emp11', 5, 6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO employee (companyId, teamId, name, daysOff, specialDaysOff, joinDate, endDate) VALUES (1, 2, 'emp22', 7, 8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO employee (companyId, teamId, name, daysOff, specialDaysOff, joinDate, endDate) VALUES (1, 2, 'emp33', 9, 10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO daysOff (companyId, employeeId, day) VALUES (1, 1, CURRENT_TIMESTAMP());
INSERT INTO daysOff (companyId, employeeId, day) VALUES (1, 1, CURRENT_TIMESTAMP());
INSERT INTO daysOff (companyId, employeeId, day) VALUES (1, 1, CURRENT_TIMESTAMP());

INSERT INTO daysOff (companyId, employeeId, day) VALUES (1, 2, CURRENT_TIMESTAMP());


INSERT INTO specialDaysOff (companyId, employeeId, day) VALUES (1, 1, CURRENT_TIMESTAMP());
INSERT INTO specialDaysOff (companyId, employeeId, day) VALUES (1, 1, CURRENT_TIMESTAMP());
INSERT INTO specialDaysOff (companyId, employeeId, day) VALUES (1, 1, CURRENT_TIMESTAMP());

INSERT INTO specialDaysOff (companyId, employeeId, day) VALUES (1, 2, CURRENT_TIMESTAMP());


INSERT INTO task (companyId, teamId, code, name, description, totalHours, startDate, endDate) VALUES (1, 1, 'T1', 'Task1', 'Description1', 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO task (companyId, teamId, code, name, description, totalHours, startDate, endDate) VALUES (1, 1, 'T2', 'Task2', 'Description2', 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO task (companyId, teamId, code, name, description, totalHours, startDate, endDate) VALUES (1, 1, 'T3', 'Task3', 'Description3', 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO task (companyId, teamId, code, name, description, totalHours, startDate, endDate) VALUES (1, 1, 'T4', 'Task4', 'Description4', 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


INSERT INTO taskAssociations (companyId, employeeId, taskId) VALUES (1, 1, 1);
INSERT INTO taskAssociations (companyId, employeeId, taskId) VALUES (1, 2, 1);
INSERT INTO taskAssociations (companyId, employeeId, taskId) VALUES (1, 3, 1);
INSERT INTO taskAssociations (companyId, employeeId, taskId) VALUES (1, 1, 2);
INSERT INTO taskAssociations (companyId, employeeId, taskId) VALUES (1, 1, 3);


INSERT INTO requests (companyId, employeeId, type, description, requestDate) VALUES (1, 3, 'type1', 'desc1', CURRENT_TIMESTAMP());
INSERT INTO requests (companyId, employeeId, type, description, requestDate) VALUES (1, 3, 'type2', 'desc3', CURRENT_TIMESTAMP());
INSERT INTO requests (companyId, employeeId, type, description, requestDate) VALUES (1, 3, 'type3', 'desc3', CURRENT_TIMESTAMP());


INSERT INTO workedHours (companyId, employeeId, taskId, date, hours) VALUES (1, 1, 1, CURRENT_TIMESTAMP(), 3);
INSERT INTO workedHours (companyId, employeeId, taskId, date, hours) VALUES (1, 1, 1, CURRENT_TIMESTAMP(), 5);
INSERT INTO workedHours (companyId, employeeId, taskId, date, hours) VALUES (1, 1, 1, CURRENT_TIMESTAMP(), 6);
INSERT INTO workedHours (companyId, employeeId, taskId, date, hours) VALUES (1, 1, 1, CURRENT_TIMESTAMP(), 7);
INSERT INTO workedHours (companyId, employeeId, taskId, date, hours) VALUES (1, 1, 2, CURRENT_TIMESTAMP(), 4);
