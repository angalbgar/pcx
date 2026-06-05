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
                    <li><a href="/noticias">Noticias</a></li>
                    <li><a href="/quien-somos">Quién somos</a></li>
                </ul>
            </nav>
        </header>
    );
}

export default Header;

