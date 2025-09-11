import './styles/style.css';
import { Route, Routes } from 'react-router-dom';
import Header from '@/components/layout/Header.tsx';
import Footer from '@/components/layout/Footer.tsx';
import AuctionHome from '@/pages/AuctionHome.tsx';

export function App() {
  return (
    <div className="size-full max-w-md mx-auto bg-background">
      <Header />
      <main>
        <Routes>
          <Route path="/" element={<AuctionHome />} />
        </Routes>
      </main>
      <Footer />
    </div>
  );
}
