import React from "react";
import "../Main.css";

function Main() {
    return (
        <>
            <div className="titulo-main">
                <h1>Página Principal</h1>
            </div>
            
    <div className="contenedor">
        <div>
            <img className="img" src="./public/Foto_Hacker.jpg"/>

            <h2>Título</h2>
            <p>Este texto aparecerá a la derecha de la imagen.</p>
        </div>
    </div>
            <span>
                <p>ProjectCodeX es una plataforma de cibeseguridad orientada a centralizar soluciones de tu día a día.</p>
                <p>Visita el Blog para comprobar qué consejos o buenas prácticas tenemos para ofrecerte.</p>
                <p></p>                
            </span>
        </>

    );
}

export default Main;
