import React from "react";
import "../Header.css";
import { Link } from "react-router-dom";


function Header() {
    return (
        
        <header className="header">
            <div className="header__logo">
                <Link to="/"><img src="./public/logo.png"/></Link>
                <h1><Link to="/">ProjectCodeX Web</Link></h1>

            </div>

            <nav className="header__nav">
                <ul>
                    <li><Link to="/blog">Blog</Link></li>
                    <li><Link to="/noticias">Noticias</Link></li>
                    <li><Link to="/quien-somos">Quién Somos</Link></li>
                </ul>
            </nav>
        </header>
    );
}

export default Header;

