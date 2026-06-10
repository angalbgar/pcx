import React from "react";
import "../Main.css";
import { Link } from "react-router-dom";

function Main() {
    return (
        <>
            <div className="titulo-main">
                <h1>Página Principal</h1>
            </div>
            
    <div className="contenedor_principal1">
            <h2 className="titulo-main2">¿Qué es ProjectCodeX?</h2>
            <p>ProjectCodeX es una empresa de ciberseguridad especializada en ofrecer soluciones a la gente para que pueda mantener su seguridad en el mundo digital.</p>
            <img src="./main.gif"/>
            <br/>
            <h2 className="titulo-main2">¿Qué ofrecemos?</h2>
            <br/>
            <ul>
                <li>Acceso a la aplicación beta de seguridad</li>
                <li>Funciones de seguridad para comprobar integridad de archivos y contraseñas filtradas.</li>
                <li>Acceso a una comunidad privada carismática.</li>
            </ul>
            
            <p> Por último en esta web podrás acceder al       <Link className="enlace" to="/blog">
          Blog
      </Link> para leer todas nuestras recomendaciones.</p>
    </div>
        </>
    );
}

export default Main;
