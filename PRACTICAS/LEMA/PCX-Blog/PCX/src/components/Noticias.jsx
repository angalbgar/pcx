// Noticias.jsx
import React from "react";
import "../Noticias.css"; 

// Esta funcionalidad la he hecho con IA porque el tema de los iframes quería hacerlo algo más bonito que ponerlo en html básico.

const Noticias = () => {
  const sitios = [
    { nombre: "CibersecurityNews", url: "https://cybersecuritynews.es/" },
    { nombre: "Incibe", url: "https://www.incibe.es/" },
    { nombre: "EuropaPress", url: "https://www.europapress.es/temas/ciberseguridad/" },
    { nombre: "20minutos", url: "https://www.20minutos.es/tags/temas/ciberseguridad.html" },
  ];

  return (
    <div className="noticias-container">
      <h1>Noticias en línea</h1>
      <div className="iframes-grid">
        {sitios.map((sitio, index) => (
          <div key={index} className="iframe-wrapper">
            <h3>{sitio.nombre}</h3>
            <iframe
              src={sitio.url}
              title={sitio.nombre}
              frameBorder="0"
              allowFullScreen
            ></iframe>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Noticias;