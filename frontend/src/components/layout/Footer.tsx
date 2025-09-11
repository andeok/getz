import { Home, MessageCircle, User } from 'lucide-react';
import { Link } from 'react-router-dom';

export default function Footer() {
  const buttonClasses =
    'flex flex-col items-center justify-center gap-1 h-full rounded-none flex-1 hover:bg-gray-100';

  return (
    <footer className="fixed bottom-0 left-0 right-0 h-16 bg-white border-t flex justify-around max-w-md mx-auto">
      <Link to="/" className={buttonClasses}>
        <Home className="w-5 h-5" />
        <span className="text-xs">홈</span>
      </Link>
      <Link to="/chat" className={buttonClasses}>
        <MessageCircle className="w-5 h-5" />
        <span className="text-xs">채팅</span>
      </Link>
      <Link to="/my" className={buttonClasses}>
        <User className="w-5 h-5" />
        <span className="text-xs">내정보</span>
      </Link>
    </footer>
  );
}
