import { Button } from '@/components/ui/button.tsx';
import { Menu, Search } from 'lucide-react';

export default function Header() {
  return (
    <div className="sticky top-0 bg-background/95 backdrop-blur-sm border-b p-4 z-10">
      <div className="flex items-center justify-between">
        <h1 className="text-lg font-semibold">🔨 경매장</h1>
        <div className="flex gap-2">
          <Button
            variant="ghost"
            size="icon"
            // onClick={onSearchClick}
          >
            <Search className="w-5 h-5" />
          </Button>
          <Button
            variant="ghost"
            size="icon"
            // onClick={() => setShowCategoryMenu(!showCategoryMenu)}
          >
            <Menu className="w-5 h-5" />
          </Button>
        </div>
      </div>
    </div>
  );
}
