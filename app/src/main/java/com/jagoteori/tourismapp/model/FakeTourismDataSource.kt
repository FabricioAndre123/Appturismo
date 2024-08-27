package com.jagoteori.tourismapp.model

import com.jagoteori.tourismapp.R

object FakeTourismDataSource {
    private val dummySchedule = listOf(
        Schedule(
            id = 1,
            day = "LUNES",
            date = "19 AGOSTO 2024",
            isAvailable = true,
            isSelected = false,
        ),
        Schedule(
            id = 2,
            day = "MARTES",
            date = "20 OCTUBRE 2024",
            isAvailable = true,
            isSelected = false,
        ),
        Schedule(
            id = 3,
            day = "MIERCOLES",
            date = "21 NOVIEMBRE 2024",
            isAvailable = false,
            isSelected = false,
        ),
        Schedule(
            id = 4,
            day = "JUEVES",
            date = "22 DICIEMBRE 2024",
            isAvailable = false,
            isSelected = false,
        ),
        Schedule(
            id = 5,
            day = "VIERNES",
            date = "01 ENERO 2025",
            isAvailable = true,
            isSelected = false,
        ),
    )

    val dummyTourism = listOf(
        Tourism(
            id = 1,
            name = "BAÑOS DE AGUA SANTA",
            location = "Baños, Tungurahua",
            rate = "",
            description =
            "Baños de Agua Santa es una ciudad de la provincia de Tungurahua en Ecuador y es una vía de acceso a la cuenca del Amazonas. Es conocida por su acceso a senderos en el volcán activo Tungurahua al sur y por sus aguas termales ricas en minerales.",
            ticketPrice = "\$ 10",
            picture = R.drawable.image_destination1,
            isFavorite = false,
            schedule = dummySchedule
        ),
        Tourism(
            id = 2,
            name = "Laguna Quilotoa",
            location = "Cotopaxi, Ecuador",
            rate = "",
            description =
            "La Laguna Quilotoa es un cráter volcánico lleno de agua que ofrece vistas impresionantes de un lago esmeralda rodeado por montañas. Es un destino popular para senderismo y fotografía.",
            ticketPrice = "\$ 3",
            picture = R.drawable.image_quilotoa,
            isFavorite = false,
            schedule = dummySchedule
        ),
        Tourism(
            id = 3,
            name = "Islas Galápagos",
            location = "Galápagos, Ecuador",
            rate = "",
            description =
            "Las Islas Galápagos son famosas por su biodiversidad única y su historia en la teoría de la evolución de Charles Darwin. Los visitantes pueden explorar la vida silvestre tanto en tierra como en el mar.",
            ticketPrice = "\$ 100",
            picture = R.drawable.image_galapagos,
            isFavorite = false,
            schedule = dummySchedule
        ),
        Tourism(
            id = 4,
            name = "Parque Nacional Cotopaxi",
            location = "Cotopaxi, Ecuador",
            rate = "",
            description =
            "El Parque Nacional Cotopaxi es conocido por su volcán activo, el Cotopaxi, que ofrece oportunidades para montañismo, senderismo y observación de la vida silvestre en un entorno espectacular.",
            ticketPrice = "\$ 10",
            picture = R.drawable.image_cotopaxi,
            isFavorite = false,
            schedule = dummySchedule
        ),
        Tourism(
            id = 5,
            name = "Parque Nacional Yasuní",
            location = "Orellana, Ecuador",
            rate = "",
            description =
            "El Parque Nacional Yasuní es una de las áreas más biodiversas del planeta. Es hogar de una increíble variedad de especies de flora y fauna, y es una experiencia única para los amantes de la naturaleza.",
            ticketPrice = "\$ 8",
            picture = R.drawable.image_yasuni,
            isFavorite = false,
            schedule = dummySchedule
        ),
        Tourism(
            id = 6,
            name = "Mitad del Mundo",
            location = "Quito, Ecuador",
            rate = "",
            description =
            "El monumento a la Mitad del Mundo marca la ubicación exacta de la línea ecuatorial, donde los visitantes pueden pararse con un pie en cada hemisferio. Es un destino educativo y turístico muy popular.",
            ticketPrice = "\$ 7",
            picture = R.drawable.image_mitad_del_mundo,
            isFavorite = false,
            schedule = dummySchedule
        ),
        Tourism(
            id = 7,
            name = "Cuenca",
            location = "Azuay, Ecuador",
            rate = "",
            description =
            "Cuenca es una ciudad colonial bien conservada, conocida por su arquitectura, sus iglesias, y sus alrededores naturales. Es un sitio del Patrimonio Mundial de la UNESCO.",
            ticketPrice = "\$ 8",
            picture = R.drawable.image_cuenca,
            isFavorite = false,
            schedule = dummySchedule
        ),
        Tourism(
            id = 8,
            name = "Montañita",
            location = "Santa Elena, Ecuador",
            rate = "",
            description =
            "Montañita es una playa famosa por su ambiente vibrante y relajado, ideal para surfistas y turistas que buscan una experiencia de playa animada con una vida nocturna activa.",
            ticketPrice = "\$ 6.5", // Gratis para acceder a la playa
            picture = R.drawable.image_montanita,
            isFavorite = false,
            schedule = dummySchedule
        ),
        Tourism(
            id = 9,
            name = "Salinas",
            location = "Santa Elena, Ecuador",
            rate = "",
            description =
            "Salinas es una conocida ciudad costera con playas de arena dorada, ideal para disfrutar de deportes acuáticos y una variedad de restaurantes y tiendas frente al mar.",
            ticketPrice = "\$ 7", // Gratis para acceder a las playas
            picture = R.drawable.image_salinas,
            isFavorite = false,
            schedule = dummySchedule
        ),

        Tourism(
            id = 10,
            name = "Playas",
            location = "Guayas, Ecuador",
            rate = "4.5",
            description =
            "Playas es un destino popular por sus playas extensas y tranquilas, ofreciendo un ambiente relajante para familias y turistas que buscan descansar junto al mar.",
            ticketPrice = "\$ 8", // Gratis para acceder a las playas
            picture = R.drawable.image_playas,
            isFavorite = false,
            schedule = dummySchedule
        ),
    )


}