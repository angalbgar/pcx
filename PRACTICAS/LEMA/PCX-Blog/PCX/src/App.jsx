import { useState } from 'react'

import './index.css'

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Footer from './components/Footer'
import Header from './components/Header'
import Blog from './components/Blog'

import ProtegerCuentas from './components/Entradas-Blog/ProtegerCuentas';
import Contraseñas from './components/Entradas-Blog/Contraseñas';
import Autenticacion from './components/Entradas-Blog/Autenticacion';
import Malware from './components/Entradas-Blog/Malware';
import Ransomware from './components/Entradas-Blog/Ransomware';
import RedesSociales from './components/Entradas-Blog/RedesSociales';
import Noticias from './components/Noticias';
import Main from './components/Main';
import Contacto from './components/Contacto';

function App() {
  return (
    <>
    <Router>
      <Header />

      <Routes>
        <Route path="/" element={<Main />}/>
      </Routes> 

      <Routes>
        <Route path="/noticias" element={<Noticias />}/>
      </Routes> 

            <Routes>
        <Route path="/quien-somos" element={<Contacto />}/>
      </Routes> 

      <Routes>
        <Route path="/blog" element={<Blog />} />
      </Routes>

      <Routes>
        <Route path="/blog/entrada01" element={<ProtegerCuentas />}/>
      </Routes> 

     <Routes>
        <Route path="/blog/entrada02" element={<Contraseñas />}/>
      </Routes>
  
       <Routes>
      <Route path="/blog/entrada03" element={<Malware />}/>
      </Routes>
     
     <Routes>
        <Route path="/blog/entrada04" element={<Autenticacion />}/>
      </Routes>
      
      <Routes>
        <Route path="/blog/entrada05" element={<Ransomware />}/>
      </Routes>

      <Routes>
        <Route path="/blog/entrada06" element={<RedesSociales />}/>
      </Routes>

      <Footer />
    </Router>
    </>
  )
}

export default App
