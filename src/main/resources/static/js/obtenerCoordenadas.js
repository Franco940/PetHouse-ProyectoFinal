let objetoCoordenadas = {
    lat: 0,
    lng: 0
};

async function buscarCoordenadasUbicacion(){
    let lugarParaBuscar = document.getElementById("lugar").value;

    const request = await fetch("api/lugar",{
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

    console.log(response.lat)
    console.log(response.lng)

    initMap()
}
