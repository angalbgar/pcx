import React from "react";
import "../../Blog.css"
import { Link } from "react-router-dom";

function ProtegerCuentas() {
    return (
<article className="entrada-publicada">
  <h1>Cómo proteger tus cuentas frente al phishing</h1>

  <p className="fecha">4 de junio de 2026</p>

  <p>
    El phishing es una de las técnicas más utilizadas por los ciberdelincuentes
    para robar información personal, contraseñas y datos bancarios. Consiste en
    hacerse pasar por una entidad legítima mediante correos electrónicos,
    mensajes o páginas web falsas.
  </p>

  <h2>¿Cómo funciona un ataque de phishing?</h2>

  <p>
    Normalmente la víctima recibe un mensaje que aparenta proceder de una
    empresa conocida, como un banco o una red social. El mensaje incluye un
    enlace que dirige a una página falsa diseñada para capturar las credenciales
    del usuario.
  </p>

  <h2>Consejos para evitar ser víctima</h2>

  <ul>
    <li>Verifica siempre la dirección del remitente.</li>
    <li>No pulses enlaces sospechosos.</li>
    <li>Comprueba la URL antes de introducir datos.</li>
    <li>Activa la autenticación en dos factores.</li>
    <li>Mantén actualizado tu navegador y antivirus.</li>
  </ul>

  <h2>Conclusión</h2>

  <p>
    La mejor defensa contra el phishing es la formación y la precaución.
    Dedicar unos segundos a verificar la autenticidad de un mensaje puede evitar
    importantes pérdidas de información y dinero.
  </p>

      <Link className="enlace" to="/blog">
          Atrás
      </Link>

</article>

    );
}

export default ProtegerCuentas;

