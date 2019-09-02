	USE `mydb` ;
delimiter $
create procedure login (in nUsuario varchar(10), in pass varchar(15), out sRol varchar(25), out idC varchar(15), out idE varchar(15))
	begin
		set sRol = null;
        set idC = null;
        set idE = null;
		select tipo, idCliente, idEmpleado into sRol, idC, idE
		from usuario u
		where u.usuario = nUsuario  and u.contrasena = pass;
	end $
delimiter ;

delimiter $$
create procedure obtenerEmpleados()
	begin
		select e.idEmpleado, e.nombre, e.apellido, e.direccion, e.telefono, e.estado, e.sueldo, e.turno, e.tipo, u.idUsuario, u.usuario,u.contrasena
        from usuario u, empleado e, hotel h
        where e.idHotel = h.idHotel and e.idEmpleado = u.idEmpleado;
	end $$
delimiter ;


delimiter $$
create procedure obtenerClientes()
	begin
		select c.idCliente, c.nombre, c.apellido, c.direccion, c.telefono, u.idUsuario, u.usuario, u.contrasena
        from usuario u, cliente c
        where u.idCliente = c.idCliente;
	end $$
delimiter ;

create view vistaObtenerHabitacion as 
	select h.idHabitacion, h.numeroHabitacion, h.descripcion, h.numeroDeCama, h.estado
        from habitacion h
        where h.estado <> false;
        
delimiter $$
create procedure obtenerHabitacion()
	begin
		select * from vistaObtenerHabitacion;
	end $$
delimiter ;

delimiter $$
create procedure obtenerTipoPago()
	begin
		select t.idTipo, t.tipo, t.Descripcion
        from tipoPago t;
	end $$
delimiter ;

create view vistaObtenerReservacion as
	select r.idReserva, r.fechaInicio, r.fechaFin, r.detalle, r.total, r.estado, r.idCliente, h.numeroHabitacion
        from reservacion r, habitacion h
        where r.idHabitacion = h.idHabitacion;

delimiter $$
create procedure obtenerReservacion()
	begin
		select * from vistaObtenerReservacion;
	end $$
delimiter ;

delimiter $$
create procedure obtenerTipoHabitacion()
	begin
		select idTipo, nombre, precio 
        from tipoHabitacion;
	end $$
delimiter ;


delimiter $$
create procedure actualizarReservacion(in id int, in fechai date, in fechaf date,deta varchar(45), in tot float, in idc varchar(15), in idh varchar(5), in est boolean)
	begin
		DECLARE idha int;
        set idha = (select idHabitacion from habitacion where numeroHabitacion = idh limit 1);
		update reservacion
        set
		  fechaInicio = fechai,
		  fechaFin = fechaf,
		  detalle = deta,
		  estado = est,
		  total = tot,
		  idCliente = idc,
		  idHabitacion = idha,
		  idTipoP = 1
		where idReserva = id ;
	end $$
delimiter ;


delimiter $$
create procedure ingresarReservacion(in id int, in fechai date, in fechaf date,deta varchar(45), in tot float, in idc varchar(15), in idh varchar(5), in est boolean)
	begin
    DECLARE idha int;
	set idha = (select idHabitacion from habitacion where numeroHabitacion = idh limit 1) ;
	insert into reservacion(`fechaInicio`,`fechaInicio`,`fechaFin`,`detalle`,`estado`,`total`,`idCliente`,`idHabitacion`,`idTipoP`) 
		values(fechai,fechaf,deta,true,tot,idc,idha,1);
    end $$
delimiter ;

delimiter $$
create procedure eliminarReservacion(in id int)
	begin
		update reservacion r
        set
            r.estado = false
		where idReserva = id;
	end $$
delimiter ;


delimiter $$
create procedure actualizarEmpleados(in ide varchar(15), in nom varchar(40), in ape varchar(40), in dir varchar(40), in tele varchar(15), in est boolean,
		in sue float, in tur char(1), in tip varchar(15), in usu varchar(15), in cla varchar(15))
	begin
		update empleado
        set
		  nombre = nom,
          apellido = ape,
          direccion = dir,
          telefono = tele,
          estado = est,
          sueldo = sue,
          turno = tur,
          tipo = tip
		where idEmpleado = ide ;
        update usuario
        set
		  usuario = usu,
          contrasena = cla
		where idEmpleado = ide ;
	end $$
delimiter ;

delimiter $$
create procedure ingresarEmpleados(in ide varchar(15), in nom varchar(40), in ape varchar(40), in dir varchar(40), in tele varchar(15), in est boolean, in sue float, 
		in tur char(1), in tip varchar(15), in usu varchar(15), in cla varchar(15))
	begin
    insert into empleado()
		values(ide,nom,ape,dir,tele,est,sue,tur,tip,1);
	insert into usuario(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
		values(usu,cla,true,'empleado',null,ide);
	end $$
delimiter ;

delimiter $$
create procedure eliminarEmpleados(in ide varchar(15))
	begin
		update empleado
        set
          estado = false
		where idEmpleado = ide ;
        update usuario
        set
		  estado = false
		where idCliente = ide ;
	end $$
delimiter ;


delimiter $$
create procedure actualizarCliente(in ide varchar(15), in nom varchar(40), in ape varchar(40), in dir varchar(40), in tele varchar(15),in usu varchar(15), in cla varchar(15))
	begin
		update cliente
        set
		  nombre = nom,
          apellido = ape,
          direccion = dir,
          telefono = tele
		where idCliente = ide ;
        update usuario
        set
		  usuario = usu,
          contrasena = cla
		where idCliente = ide ;
	end $$
delimiter ;

delimiter $$
create procedure ingresarCliente(in ide varchar(15), in nom varchar(40), in ape varchar(40), in dir varchar(40), in tele varchar(15),in usu varchar(15), in cla varchar(15))
	begin
    insert into cliente()
		values(ide,nom,ape,dir,tele);
	insert into usuario(`usuario`,`contrasena`,`estado`,`tipo`,`idCliente`,`idEmpleado`)
		values(usu,cla,true,'cliente',ide,null);
	end $$
delimiter ;

delimiter $$
create trigger usuarioLog after insert on reservacion
	FOR EACH ROW begin
    insert into log (`fecha`,`detalle`)
		values (now(),'Se ha guardado una nueva reservacion');
    end $$
delimiter ;

delimiter $$
create trigger clienteLog after insert on cliente
	FOR EACH ROW begin
    insert into log (`fecha`,`detalle`)
		values (now(),'Se ha guardado un nuevo cliente');
    end $$
delimiter ;

delimiter $$
create trigger empleadoLog after insert on empleado
	FOR EACH ROW begin
    insert into log (`fecha`,`detalle`)
		values (now(),'Se ha guardado un nuevo empleado');
    end $$
delimiter ;

