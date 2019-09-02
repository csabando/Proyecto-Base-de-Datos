USE `mydb` ;

insert into `mydb`.`hotel`(`nombre`,`descripcion`,`direccion`,`Ciudad`,`estado`)
	values('Resort Mount Olympus','Hotel 5 estrellas','Urdesa','Guayaquil',true);

insert into `mydb`.`empleado`()
	values('0987654321','Christopher','Sabando','Guayaquil','09123698896',true,500,'m','admin',1);

insert into `mydb`.`empleado`()
	values('0912385696','Javier','Anchundia','Guayaquil','09889612369',true,400,'t','servicio',1);
insert into `mydb`.`empleado`()
	values('0988899568','Paco','SanBenito','Guayaquil','0989696311',true,300,'m','tesorero',1);
insert into `mydb`.`empleado`()
	values('0789634896','Alanis','Andrade','Guayaquil','0969303789',true,700,'m','secretaria',1);

insert into `mydb`.`cliente`()
	values('0987321654','Guillermo','Quijije','Guayaquil','0978963698');
insert into `mydb`.`cliente`()
	values('0955698723','Jhon','Calle','Guayaquil','0988996321');
insert into `mydb`.`cliente`()
	values('0789687962','Juan','Jimenez','Quito','0999589630');
insert into `mydb`.`cliente`()
	values('0698632017','Domenica','Franco','Manabi','0985561795');
insert into `mydb`.`cliente`()
	values('0389612384','Roger','Aviles','Guayaquil-Sur','0977969681');
insert into `mydb`.`cliente`()
	values('0998825365','Rosa','Jimenez','Guayaquil-Sur','0912131618');
insert into `mydb`.`cliente`()
	values('0988985462','Bryan','Acosta','Daule','0955769321');
insert into `mydb`.`cliente`()
	values('0489789645','Naty','Salas','Manta','0983299186');
insert into `mydb`.`cliente`()
	values('0958725793','Mishell','Herrera','Bolivar','0999632196');
insert into `mydb`.`cliente`()
	values('0685963258','Pedro','Vera','Guayaquil','0979885200');


insert into `mydb`.`usuario`(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
	values('sabandoc','123',true,'empleado',null,'0987654321');
insert into `mydb`.`usuario`(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
	values('quijijeg','123',true,'cliente','0987321654',null);
insert into `mydb`.`usuario`(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
	values('callej','123',true,'cliente','0955698723',null);
insert into `mydb`.`usuario`(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
	values('juanj','123',true,'cliente','0789687962',null);
insert into `mydb`.`usuario`(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
	values('domef','123',true,'cliente','0698632017',null);
insert into `mydb`.`usuario`(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
	values('avir','123',true,'cliente','0389612384',null);
insert into `mydb`.`usuario`(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
	values('roraj','123',true,'cliente','0998825365',null);
insert into `mydb`.`usuario`(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
	values('brcet','123',true,'cliente','0988985462',null);
insert into `mydb`.`usuario`(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
	values('natys','123',true,'cliente','0489789645',null);
insert into `mydb`.`usuario`(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
	values('mishe','123',true,'cliente','0958725793',null);
insert into `mydb`.`usuario`(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
	values('pedrov','123',true,'cliente','0685963258',null);
insert into `mydb`.`usuario`(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
	values('javia','123',true,'empleado',null,'0912385696');
insert into `mydb`.`usuario`(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
	values('pacos','123',true,'cliente',null,'0988899568');
insert into `mydb`.`usuario`(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
	values('alaa','123',true,'cliente',null,'0789634896');
    

insert into `mydb`.`tipoHabitacion`(`nombre`,`precio`,`estado`)
	values('sencilla','35',true);
insert into `mydb`.`tipoHabitacion`(`nombre`,`precio`,`estado`)
	values('matrimonial','70',true);
insert into `mydb`.`tipoHabitacion`(`nombre`,`precio`,`estado`)
	values('estudio','40',true);
insert into `mydb`.`tipoHabitacion`(`nombre`,`precio`,`estado`)
	values('suit','100',true);

insert into `mydb`.`habitacion`(`numeroHabitacion`,`descripcion`,`numeroDeCama`,`estado`,`idHotel`,`idTipoH`)
	values('001','Para descansar',2,true,1,1);
insert into `mydb`.`habitacion`(`numeroHabitacion`,`descripcion`,`numeroDeCama`,`estado`,`idHotel`,`idTipoH`)
	values('002','Para pareja',1,true,1,2);
insert into `mydb`.`habitacion`(`numeroHabitacion`,`descripcion`,`numeroDeCama`,`estado`,`idHotel`,`idTipoH`)
	values('003','Para estudiar con escritorio',1,true,1,3);
insert into `mydb`.`habitacion`(`numeroHabitacion`,`descripcion`,`numeroDeCama`,`estado`,`idHotel`,`idTipoH`)
	values('004','Con todos los servicios',4,true,1,4);

insert into `mydb`.`tipoPago`(`tipo`,`Descripcion`,`estado`)
	values('TarjetaCrédito','Mastercard-Discover',true);
insert into `mydb`.`tipoPago`(`tipo`,`Descripcion`,`estado`)
	values('Efectivo','pago en el momento',true);

insert into `mydb`.`reservacion`(`fechaInicio`,`fechaFin`,`detalle`,`estado`,`total`,`idCliente`,`idHabitacion`,`idTipoP`)
	values('2019-09-01','2019-09-04','3 noches de reservacion',true,105,'0987321654',1,1);
insert into `mydb`.`reservacion`(`fechaInicio`,`fechaFin`,`detalle`,`estado`,`total`,`idCliente`,`idHabitacion`,`idTipoP`)
	values('2019-09-02','2019-09-03','Por trabajo',true,204,'0955698723',2,1);
insert into `mydb`.`reservacion`(`fechaInicio`,`fechaFin`,`detalle`,`estado`,`total`,`idCliente`,`idHabitacion`,`idTipoP`)
	values('2019-09-03','2019-09-04','Viaje en familia',true,270,'0789687962',3,1);
insert into `mydb`.`reservacion`(`fechaInicio`,`fechaFin`,`detalle`,`estado`,`total`,`idCliente`,`idHabitacion`,`idTipoP`)
	values('2019-09-05','2019-09-08','Necesidad de un estudio con pc',true,225,'0789687962',4,1);
insert into `mydb`.`reservacion`(`fechaInicio`,`fechaFin`,`detalle`,`estado`,`total`,`idCliente`,`idHabitacion`,`idTipoP`)
	values('2019-09-04','2019-09-09','Viaje para acampar',true,105,'0698632017',1,1);
insert into `mydb`.`reservacion`(`fechaInicio`,`fechaFin`,`detalle`,`estado`,`total`,`idCliente`,`idHabitacion`,`idTipoP`)
	values('2019-09-10','2019-09-15','Viaje de negocio',true,390,'0389612384',2,1);
insert into `mydb`.`reservacion`(`fechaInicio`,`fechaFin`,`detalle`,`estado`,`total`,`idCliente`,`idHabitacion`,`idTipoP`)
	values('2019-09-11','2019-09-13','Suit presidencial',true,79.5,'0998825365',3,1);
insert into `mydb`.`reservacion`(`fechaInicio`,`fechaFin`,`detalle`,`estado`,`total`,`idCliente`,`idHabitacion`,`idTipoP`)
	values('2019-09-12','2019-09-18','Habitacion sencilla',true,49,'0988985462',4,1);
insert into `mydb`.`reservacion`(`fechaInicio`,`fechaFin`,`detalle`,`estado`,`total`,`idCliente`,`idHabitacion`,`idTipoP`)
	values('2019-09-14','2019-09-22','Habitacion para dias largos',true,501,'0489789645',1,1);
insert into `mydb`.`reservacion`(`fechaInicio`,`fechaFin`,`detalle`,`estado`,`total`,`idCliente`,`idHabitacion`,`idTipoP`)
	values('2019-09-18','2019-09-26','Medio plazo',true,205,'0958725793',2,1);
insert into `mydb`.`reservacion`(`fechaInicio`,`fechaFin`,`detalle`,`estado`,`total`,`idCliente`,`idHabitacion`,`idTipoP`)
	values('2019-09-20','2019-09-25','5 dias de estancia',true,450,'0987321654',3,1);
insert into `mydb`.`reservacion`(`fechaInicio`,`fechaFin`,`detalle`,`estado`,`total`,`idCliente`,`idHabitacion`,`idTipoP`)
	values('2019-09-16','2019-09-20','4 dias para descansar',true,300,'0685963258',3,1);
insert into `mydb`.`reservacion`(`fechaInicio`,`fechaFin`,`detalle`,`estado`,`total`,`idCliente`,`idHabitacion`,`idTipoP`)
	values('2019-10-01','2019-10-12','Reservacion con tiempo',true,550,'0987321654',4,1);
insert into `mydb`.`reservacion`(`fechaInicio`,`fechaFin`,`detalle`,`estado`,`total`,`idCliente`,`idHabitacion`,`idTipoP`)
	values('2019-10-15','2019-10-30','Reservado por 15 dias',true,500,'0987321654',2,1);

