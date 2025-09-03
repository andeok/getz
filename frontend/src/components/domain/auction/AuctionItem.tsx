import { Calendar, Clock, Heart } from 'lucide-react';
import { ImageWithFallback } from '@/components/ui/ImageWithFallback.tsx';

interface Auction {
  id: string;
  image: string;
  title: string;
  startTime?: string;
  endTime?: string;
  sellerId: number;
  sellerNickname: string;
  startPrice: number;
  currentPrice: number;
  endPrice?: number;
  bidCount: number;
  likeCount: number;

  status?: string;
  timeLeft: string;
  isLiked: boolean;
}

interface AuctionItemProps {
  auction: Auction;
  onClick: (id: string) => void;
}

export function AuctionItem({ auction, onClick }: AuctionItemProps) {
  const formatPrice = (price: number) => {
    return price.toLocaleString('ko-KR') + '원';
  };
  return (
    <div
      className="bg-card rounded-lg border p-3 cursor-pointer transition-colors hover:bg-muted/50"
      onClick={() => onClick(auction.id)}
    >
      <div className="flex gap-3">
        {/* Left - Image */}
        <div className="relative flex-shrink-0">
          <ImageWithFallback
            src={auction.image}
            alt={auction.title}
            className="w-32 h-32 object-cover rounded-lg"
          />
          {auction.status === 'active' ? (
            <div className="absolute top-1 left-1 bg-red-500 text-white px-1.5 py-0.5 rounded text-xs flex items-center gap-1">
              <Clock className="w-2.5 h-2.5" />
              <span>{auction.timeLeft}</span>
            </div>
          ) : (
            <div className="absolute top-1 left-1 bg-blue-500 text-white px-1.5 py-0.5 rounded text-xs flex items-center gap-1">
              <Calendar className="w-2.5 h-2.5" />
              <span>예정</span>
            </div>
          )}
        </div>

        {/* Right - Info */}
        <div className="flex-1 min-w-0">
          <div className="mb-1">
            <h3 className="font-medium text-sm line-clamp-2">
              {auction.title}
            </h3>
          </div>
          <div className="space-y-2">
            {auction.status === 'active' ? (
              <div className="flex items-center justify-between">
                <div>
                  <p className="text-xs text-muted-foreground">현재가</p>
                  <p className="font-semibold text-primary">
                    {formatPrice(auction.currentPrice)}
                  </p>
                </div>
                <div className="text-right">
                  <p className="text-xs text-muted-foreground">
                    입찰 {auction.bidCount}회
                  </p>
                  {auction.currentPrice && (
                    <p className="text-xs text-green-600">
                      즉시 {formatPrice(auction.currentPrice)}
                    </p>
                  )}
                </div>
              </div>
            ) : (
              <div className="flex items-center justify-between">
                <div>
                  <p className="text-xs text-muted-foreground">시작가</p>
                  <p className="font-semibold text-primary">
                    {formatPrice(auction.currentPrice)}
                  </p>
                </div>
                <div className="text-right">
                  <p className="text-xs text-blue-600">경매 시작</p>
                  <p className="text-xs text-blue-600 font-medium">
                    {auction.startTime}
                  </p>
                </div>
              </div>
            )}
            <div className="flex justify-end">
              <div className="flex items-center gap-1 text-muted-foreground">
                <Heart
                  className={`w-3 h-3 ${auction.isLiked ? 'fill-red-500 text-red-500' : ''}`}
                />
                <span className="text-xs">{auction.likeCount}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
