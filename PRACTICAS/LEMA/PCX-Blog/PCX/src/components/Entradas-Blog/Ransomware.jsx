import React from "react";
import "../../Blog.css"
import { Link } from "react-router-dom";

function Ransomware() {
    return (
<article className="entrada-publicada">
  <h1>El Ransomware</h1>

  <p className="fecha">4 de junio de 2026</p>

  <p>
    El ransomware es un tipo de malware que cifra tus archivos y exige un pago para recuperarlos. Este ataque puede afectar tanto a usuarios particulares como a empresas, causando pérdida de información y daños económicos.
  </p>
    <p>
        Normalmente, el ransomware llega mediante correos electrónicos, enlaces maliciosos o descargas inseguras. Una vez activado, bloquea el acceso a archivos o al sistema completo y muestra un mensaje de rescate.
    </p>
  <h2>Medidas de prevención</h2>

    <ul>
        <li>Mantén copias de seguridad actualizadas en dispositivos externos o en la nube.</li>
        <li>No abras correos ni archivos sospechosos.</li>
        <li>Actualiza el sistema operativo y el antivirus regularmente.</li>
        <li>Segmenta tu red si trabajas con información sensible.</li>
  </ul>

  <h2>Conclusión</h2>

  <p>
El ransomware puede ser devastador, pero la prevención y las copias de seguridad son clave. Adoptar hábitos de seguridad digital ayuda a minimizar el riesgo de perder datos importantes. 
    </p>

      <Link className="enlace" to="/blog">
          Atrás
      </Link>

</article>

    );
}

export default Ransomware;

