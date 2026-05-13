create table servicio (
    id_servicio serial primary key,
    nombre varchar(50) not null,
    descripcion text not null,
    categoria varchar(50) not null,
    sede varchar(50) not null,
    beneficios text not null,
    tecnologias_implicadas text not null,
    alcance text not null,
    objetivos text not null,
    fecha_creacion date default current_date,
    estado boolean not null 
);

create table consulta(
    id_consulta serial primary key,
    titulo varchar(100) not null,
    estado varchar(20) default 'pendiente',
    descripcion text not null,
    fecha_creacion date default current_date,
    fecha_fin date,
    id_servicio int references servicio(id_servicio),
    id_cliente int not null,
    id_consultor int
);

create table mensajes(
    id_mensajeria serial primary key,
    id_consulta int references consulta(id_consulta),
    id_usuario int not null,
    descripcion text not null,
    asunto varchar(100) not null,
    fecha_creacion timestamp default current_timestamp
);

create or replace function fn_consulta_estado()
returns trigger as
$$
begin
    if new.estado not in ('pendiente','en progreso', 'finalizada') then
        raise exception 'El estado no es válido. Debe ser pendiente, en progreso o finalizada.';
    end if;        
end;
$$ language plpgsql;

create or replace trigger trg_consulta_estado
before update of estado on consulta
for each row
execute function fn_consulta_estado();

insert into servicio (nombre, descripcion, categoria, sede, beneficios, tecnologias_implicadas, alcance, objetivos, estado) values
('Automatización de Procesos', 'Servicio de automatización de procesos empresariales para mejorar la eficiencia y reducir costes.', 'Automatización', 'Delegación Madrid', 'Mejora de la eficiencia operativa, reducción de costes, aumento de la productividad.', 'Inteligencia Artificial', 'Empresas de todos los tamaños que buscan automatizar sus procesos y mejorar su eficiencia.', 'Automatización de procesos empresariales', true),
('Análisis de Datos', 'Servicio de análisis de datos para ayudar a las empresas a tomar decisiones informadas basadas en datos.', 'Análisis de Datos', 'Delegación Barcelona', 'Toma de decisiones informada, identificación de oportunidades de negocio, mejora del rendimiento.', 'Big Data, Machine Learning', 'Empresas que buscan aprovechar sus datos para mejorar su rendimiento y tomar decisiones informadas.', 'Análisis de datos empresariales', true),
('Ciberseguridad', 'Servicio de ciberseguridad para proteger los sistemas y datos de las empresas contra amenazas cibernéticas.', 'Ciberseguridad', 'Delegación Valencia', 'Protección contra amenazas cibernéticas, cumplimiento de normativas de seguridad, tranquilidad para los clientes.', 'Seguridad Informática, Firewalls, Antivirus', 'Empresas que buscan proteger sus sistemas y datos contra amenazas cibernéticas.', 'Protección contra amenazas cibernéticas', true),
('Desarrollo de Software a Medida', 'Servicio de desarrollo de software personalizado para satisfacer las necesidades específicas de cada cliente.', 'Desarrollo de Software', 'Delegación Sevilla', 'Soluciones personalizadas, mejora de la eficiencia operativa, ventaja competitiva.', 'Programación, Desarrollo Web, Desarrollo Móvil', 'Empresas que buscan soluciones de software personalizadas para mejorar su eficiencia operativa y obtener una ventaja competitiva.', 'Desarrollo de software personalizado', false);