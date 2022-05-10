function initMap() {

    const ubicacion = { lat: objetoCoordenadas.lat, lng: objetoCoordenadas.lng };
  
    // Si las coordendas son iguales a esas, significa que son las coordenadas para mostrar la
    // ciudad buenos aires
     
    if(ubicacion["lat"] != -34.6042725 && ubicacion["lng"] != -58.446807){

      const map = new google.maps.Map(document.getElementById("mapa"), {
        zoom: 16,
        center: ubicacion,
      });

      const marker = new google.maps.Marker({
        position: ubicacion,
        map: map,
        title: "Hogar del cuidador"
      });

    }else{
      const map = new google.maps.Map(document.getElementById("mapa"), {
        zoom: 10,
        center: ubicacion,
      });

    }
  }
  
  window.initMap = initMap;
  