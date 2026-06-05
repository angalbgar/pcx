import { useState } from 'react'

import './index.css'

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Footer from './components/Footer'
import Header from './components/Header'
import Blog from './components/Blog'
import ProtegerCuentas from './components/Entradas-Blog/ProtegerCuentas';
import Main from './components/Main'


function App() {
  return (
    <>
    <Router>
      <Header />

      <Routes>
        <Route path="/" element={<Main />}/>
      </Routes> 

      <Routes>
        <Route path="/blog" element={<Blog />} />
      </Routes>
      <Routes>
        <Route path="/blog/proteger-cuentas" element={<ProtegerCuentas />}/>
      </Routes> 

      <Footer />
    </Router>
    </>
  )
}

export default App
