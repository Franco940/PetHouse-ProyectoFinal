function initMap() {

    const ubicacion = { lat: objetoCoordenadas.lat, lng: objetoCoordenadas.lng };
  
    const map = new google.maps.Map(document.getElementById("mapa"), {
      zoom: 16,
      center: ubicacion,
    });
  
    const marker = new google.maps.Marker({
      position: ubicacion,
      map: map,
      title: "Hogar del cuidador"
    });
  }
  
  window.initMap = initMap;