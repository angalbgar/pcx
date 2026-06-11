import { Link } from "react-router-dom";
import "../Blog.css";

function Blog() {
  return (
    <div className="blog">

      <div className="entrada-presentacion">
        
        <h2>Cómo proteger tus cuentas</h2>

        <p className="fecha">4 de junio de 2026</p>

        <p>
          Aprende a cómo proteger correctamente tus cuentas de correo electrónico, redes sociales y cualquier cuenta.
        </p>

        <Link className="enlace" to="/blog/entrada01">
          Leer más
        </Link>
      </div>
    
      <div className="entrada-presentacion">
        
        <h2>Contraseñas seguras</h2>

        <p className="fecha">4 de junio de 2026</p>

        <p>
          Se explican conceptos para fortalecer tus contraseñas.
        </p>

        <Link className="enlace" to="/blog/entrada02">
          Leer más
        </Link>

      </div>
      <div className="entrada-presentacion">
        <h2>Conocimientos de lo que es un Malware</h2>
        <p className="fecha">4 de junio de 2026</p>

        <p>
            Aprende lo básico de un malware y algunas medidas para protegerte y estar así más seguro.
        </p>

        <Link className="enlace" to="/blog/entrada03">
          Leer más
        </Link>
      </div>

      <div className="entrada-presentacion">
      
      <h2>La Autenticación MultiFactor - MFA</h2>

        <p className="fecha">4 de junio de 2026</p>

        <p>
          Aprende un poquito por qué es tan importante esta medida de seguridad hoy en día.
        </p>

        <Link className="enlace" to="/blog/entrada04">
          Leer más
        </Link>
      </div>

      <div className="entrada-presentacion">
        <h2>El Famoso Ransomware</h2>

        <p className="fecha">4 de junio de 2026</p>

        <p>
          El famoso ransomware que si se mete en tu sistema puede hacerte colapsar. Aquí te contamos medidas de protección.
        </p>

        <Link className="enlace" to="/blog/entrada05">
          Leer más
        </Link>
      </div>

      <div className="entrada-presentacion">
        <h2>Las Redes Sociales...</h2>

        <p className="fecha">4 de junio de 2026</p>

        <p>
          Se relatan los problemas habituales de estas plataformas.
        </p>

        <Link className="enlace" to="/blog/entrada06">
          Leer más
        </Link>
      </div>

      

    </div>
  );
}

export default Blog;