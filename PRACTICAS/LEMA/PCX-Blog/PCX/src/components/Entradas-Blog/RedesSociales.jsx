import React from "react";
import "../../Blog.css"
import { Link } from "react-router-dom";

function RedesSociales() {
    return (
<article className="entrada-publicada">
  <h1>Las Redes Sociales</h1>

  <p className="fecha">4 de junio de 2026</p>

  <p>
 Las redes sociales son plataformas muy utilizadas, pero también un objetivo frecuente para ciberdelincuentes que buscan robar información personal, suplantar identidades o propagar estafas.
  </p>

  <h2> Riesgos comunes</h2>
        <ul>
            <li>Publicación de datos sensibles que pueden ser utilizados en ataques de ingeniería social.</li>
            <li>Mensajes de phishing a través de mensajes directos.</li>
            <li>Suplantación de identidad mediante perfiles falsos.</li>
        </ul>

  <h2>Buenas prácticas de seguridad</h2>

  <ul>
        <li>Configura la privacidad de tu perfil para limitar quién puede ver tu información.</li>
        <li>No aceptes solicitudes de personas desconocidas.</li>
        <li>Usa contraseñas fuertes y MFA para tus cuentas.</li>
        <li>Piensa antes de compartir datos sensibles o ubicación.</li>
  </ul>

  <h2>Conclusión</h2>

  <p>
La seguridad en redes sociales depende en gran medida del usuario. Aplicando buenas prácticas y manteniendo la información bajo control, se puede disfrutar de estas plataformas sin comprometer la privacidad.
  </p>

      <Link className="enlace" to="/blog">
          Atrás
      </Link>

</article>

    );
}

export default RedesSociales;

