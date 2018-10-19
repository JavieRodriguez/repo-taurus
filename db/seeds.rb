# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)
# user = CreateAdminService.new.call
# puts 'CREATED ADMIN USER: ' << user.email

User.create!([{
    email: "javier890302@gmail.com",
    name: "Javier Andres",
    password: "javier",
    role: "user"
}
])

User.create!([{
    email: "usuario@hotmail.com",
    name: "usuairo",
    password: "12345678",
    role: "user"
}
])

Modalidad.create!([{
    id: 1, 
    nombre: "MINIMA CUANTÍA"
}, {
    id: 2, 
    nombre: "CONTRATACIÓN DIRECTA"
},
{
    id: 3, 
    nombre: "SELECCIÓN ABREVIADA"
}, 
{
    id: 4, 
    nombre: "CONCURSO DE MÉRITOS"
}, 
{
    id: 5, 
    nombre: "LICITACIÓN PÚBLICA"
},
{
    id: 6, 
    nombre: "REGIMEN ESPECIAL"
}])

Etapa.create!([{
    id: 1, 
    nombre: "Etapa de planeación o preparatoria (Estudios Previos)", 
    modalidad_id: 1
}, 
{
    id: 2, 
    nombre: "Etapa Precontractual", 
    modalidad_id: 1
},
{
    id: 3, 
    nombre: "Etapa Contractual", 
    modalidad_id: 1
}, 
{
    id: 4, 
    nombre: "Etapa Post Contractual", 
    modalidad_id: 1
}])

Actividad.create!([{
    id: 1, 
    descripcion: "ANALISIS DEL SECTOR ECONÓMICO Y ANALISIS DEL MERCADO PARA OBTENER PRESUPUESTO OFICIAL", 
    etapa_id: 1
}, 
{
    id: 2, 
    descripcion: "DESCRIPCIÓN, ALCANCE, DELIMITACIÓN DEL OBJETO Y CLASIFICACIÓN", 
    etapa_id: 1
},
{
    id: 3, 
    descripcion: "IMPUTACIÓN PRESUPUESTAL Y FORMA DE PAGO", 
    etapa_id: 1
},
{
    id: 4, 
    descripcion: "ESTUDIO Y JUSTIFICACIÓN DE LA NECESIDAD", 
    etapa_id: 1
},
{
    id: 5, 
    descripcion: "ANALISIS DE RIESGO Y ESTUDIO DE GARANTÍAS", 
    etapa_id: 1
}])

Tipocontrato.create!([{
    id:   1, 
    tipo: "Compraventa"
}, 
{
    id: 2, 
    tipo: "Suministro"
},
{
    id: 3, 
    tipo: "Prestación  de Servicios"
},
{
    id: 4, 
    tipo: "Obra"
},
{
    id: 5, 
    tipo: "Consultoría"
},
{
    id: 6, 
    tipo: "Arrendamiento"
}])


