# TallerRetrofit

Intrucción generales:

Posiblemente no les corra con nada más clonar el proyecto pueda que sea necesario configurar el local.properties del proyecto al tenerlo descargado en su maquina, para eso únicamente
debe colocar la ruta de su SDK 

Ejemplo: (Posiblemente así estará al momento de clonar)
sdk.dir=C\:\\Users\\Daniel\\AppData\\Local\\Android\\Sdk

Ustedes lo cambiarían a:

sdk.dir=C\:\\Users\\(Tu usuario)\\AppData\\Local\\Android\\Sdk


Retrofit 1:

Aquí únicamente pueden manejar sus datos utilizando JSONArray y JSONObject, gracias al converter que se utiliza en ApiRest es posible convertir la respuesta del ws en una
cadena y así luego parsearla en un JSONArray o JSONObject dependiendo la situación.


Retrofit 2:

Aquí es donde tuve el inconveniente en la presentación, da el caso que la apiPokemon, devolve todo iniciando por un JSONObject y el converter que esta configurado en el Retrofit2
es GsonConverterFactory que es una de las dependencias que deben agregar al build.gradle de su proyecto, entonces, al querer guardar la información la configuración en el código
esperaba un JSONObject de primeras, pero el ws de GuatePreviene devuelve un JSONArray, para tenerlo más claro es un arreglo de JSONObject, entonces para arreglar eso tenía
que esperar una List<JSONObject>> y eso era todo, eso se los dejé configurado en los proyectos en cada una de las ramas.
