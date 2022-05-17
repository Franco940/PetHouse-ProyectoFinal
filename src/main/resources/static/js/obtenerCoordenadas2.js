let objetoCoordenadas = {
    // Coordenadas para mostrar la ciudad de Buenos Aires
    lng: -58.4468073,
    lat: -34.6042725
};

async function buscarCoordenadasUbicacion(lugar){
    let lugarParaBuscar = lugar;

    if(lugar == "no"){
        objetoCoordenadas["lat"] = -34.6042725;
        objetoCoordenadas["lng"] = -58.4468073;

        initMap()

        return;
    }

    const request = await fetch("../../mapa/api/coordenadas",{
        method: 'POST',
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        },
        body: JSON.stringify(lugarParaBuscar)
  });

    const response = await request.json()

    objetoCoordenadas["lat"] = parseFloat(response.lat);
    objetoCoordenadas["lng"] = parseFloat(response.lng);

    console.log("Lat: " + response.lat)
    console.log("Lng: " + response.lng)

    initMap()
}
