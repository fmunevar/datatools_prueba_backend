# Información general del proyecto

El proyecto desarrollado en JEE expone una Api/REST que cuenta con los siguienes servicios:
1. login
2. Companies: CRUD
3. Users: CRUD
4. Vehicles: CRUD
5. Check assignments: verificar la asignación de vehículos para un usuaario
6. Enroll vehicle: afilia un vehículo a una compañía
7. Assign vehicle: asocia un usuario a un vehículo

![image](https://user-images.githubusercontent.com/41402595/153773680-91f45969-1eff-4ba9-95f2-8deaf2da71b2.png)


# Información técnica
El proyecto cuenta con las siguientes clases y modelos:
## Modelos para mapeo de datos
+ AppPermissions
+ Company
+ Driving
+ Enrollment
+ User
+ UserLoginInfo
+ Vehicle

## Clases de manejo de lógicas
+ CompanyRepository
+ CompanyResource
+ UserLoginInfoRepository
+ UserLoginInfoResource
+ UserRepository
+ UserResource
+ VehicleRepository
+ VehicleResource

# Información adicional
+ Corre bajo servidor de aplicaciones ApacheTomcat v.10
+ Conexión JDBC hacia la base de datos
+ Implementa librería crypto-js para hasheo de contraseñas

NOTA: Se adjunta colección para pruebas.

********************************************

# Base de datos
La base cuenta con integridad referencial para la conexión entre las tablas.
Implementada en MySQL.

## Descripición de las tablas
1. app_permisions: ACL para el manejo de rutas por rol
2. app_roles: tabla paramétrica de roles del sistema
3. companies: información de las compañías
4. driving: asociación entre vehículos y usuarios (conductores)
5. enrollment: afiliación de vehículos y empresas
6. positions: tabla paramétrica de cargos de los usuarios
7. users: información de los usuarios
8. user_login_info: información de usuario/contraseña de los usuarios
9. vehicles: información de vehículos
10. zones: tabla recursiva que contiene la información de localización (pais->departamento->ciudad)
11. zone_types: tabla paramétrica con la información del tipo de ĺocalización.
