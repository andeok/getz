import { AuctionItem } from '@/components/domain/auction/AuctionItem.tsx';

export default function AuctionHome() {
  const mockAuctions = [
    {
      id: '1',
      title: '빈티지 라이카 카메라 M3 1962년 제조',
      currentPrice: 850000,
      buyNowPrice: 1200000,
      timeLeft: '2시간 15분',
      image:
        'https://images.unsplash.com/photo-1583056971486-b6167117fe27?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx2aW50YWdlJTIwY2FtZXJhJTIwYXVjdGlvbnxlbnwxfHx8fDE3NTc0MDc3NDR8MA&ixlib=rb-4.1.0&q=80&w=1080',
      isLiked: true,
      bidCount: 12,
      likeCount: 34,
      status: 'active',
    },
    {
      id: '2',
      title: '럭셔리 시계 컬렉션 오메가 스피드마스터',
      currentPrice: 1200000,
      timeLeft: '1일 5시간',
      image:
        'https://images.unsplash.com/photo-1680810897186-372717262131?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxsdXh1cnklMjB3YXRjaCUyMGF1Y3Rpb258ZW58MXx8fHwxNzU3MzU5MDc4fDA&ixlib=rb-4.1.0&q=80&w=1080',
      isLiked: true,
      bidCount: 8,
      likeCount: 67,
      status: 'active',
    },
    {
      id: '3',
      title: '한정판 명품 가방 루이비통 2024 콜렉션',
      currentPrice: 5800000,
      timeLeft: '',
      startTime: '9월 15일 오후 2시',
      image:
        'https://images.unsplash.com/photo-1584917865442-de89df76afd3?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxsdXh1cnklMjBiYWd8ZW58MXx8fHwxNzU3NDM1MjU0fDA&ixlib=rb-4.1.0&q=80&w=1080',
      isLiked: true,
      bidCount: 0,
      likeCount: 156,
      status: 'scheduled',
    },
    {
      id: '4',
      title: '앤티크 원목 서랍장 빈티지 가구',
      currentPrice: 450000,
      buyNowPrice: 680000,
      timeLeft: '3일 12시간',
      image:
        'https://images.unsplash.com/photo-1693405102972-a15e3e540c66?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxhbnRpcXVlJTIwZnVybml0dXJlJTIwYXVjdGlvbnxlbnwxfHx8fDE3NTc0MDc3NTN8MA&ixlib=rb-4.1.0&q=80&w=1080',
      isLiked: true,
      bidCount: 5,
      likeCount: 23,
      status: 'active',
    },
    {
      id: '5',
      title: '유명 작가 유화 작품 한국 현대미술',
      currentPrice: 2800000,
      timeLeft: '5일 8시간',
      image:
        'https://images.unsplash.com/photo-1695844707427-ecb76be6d4be?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxhcnR3b3JrJTIwcGFpbnRpbmclMjBhdWN0aW9ufGVufDF8fHx8MTc1NzQwNzc1NHww&ixlib=rb-4.1.0&q=80&w=1080',
      isLiked: true,
      bidCount: 23,
      likeCount: 89,
      status: 'active',
    },
    {
      id: '6',
      title: '클래식 기타 Martin D-28 1975년산',
      currentPrice: 1800000,
      buyNowPrice: 2500000,
      timeLeft: '6시간 42분',
      image:
        'https://images.unsplash.com/photo-1510915361894-db8b60106cb1?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxndWl0YXJ8ZW58MXx8fHwxNzU3NDM1MjY0fDA&ixlib=rb-4.1.0&q=80&w=1080',
      isLiked: true,
      bidCount: 15,
      likeCount: 45,
      status: 'active',
    },
    {
      id: '7',
      title: '아이폰 15 Pro Max 1TB 티타늄',
      currentPrice: 1800000,
      timeLeft: '',
      startTime: '9월 16일 오전 10시',
      image:
        'https://images.unsplash.com/photo-1592750475338-74b7b21085ab?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxpcGhvbmV8ZW58MXx8fHwxNzU3NDM1Mjc0fDA&ixlib=rb-4.1.0&q=80&w=1080',
      isLiked: true,
      bidCount: 0,
      likeCount: 89,
      status: 'scheduled',
    },
    {
      id: 8,
      image:
        'https://images.unsplash.com/photo-1584917865442-de89df76afd3?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxsdXh1cnklMjBiYWd8ZW58MXx8fHwxNzU3NDM1MjU0fDA&ixlib=rb-4.1.0&q=80&w=1080',
      title: '집 판다',
      startTime: '2025-09-09T10:00:00',
      endTime: '2025-09-16T23:00:00',
      sellerId: 2,
      sellerNickname: '왔다리',
      startPrice: 250000,
      currentPrice: 250000,
      endPrice: 1500000,
      bidCount: 9,
      likeCount: 93,

      status: 'active',
      timeLeft: '2일',
      isLiked: true,
    },
  ];

  const onAuctionClick = () => {};

  return (
    <div className="px-4 space-y-3">
      {mockAuctions.map((auction) => (
        // eslint-disable-next-line react-x/no-missing-key
        <AuctionItem auction={auction} onClick={onAuctionClick} />
      ))}
    </div>
  );
}
