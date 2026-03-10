export interface Product {
  id: number;
  name: string;
  brand: string;
  category: string;
  price: number;
  originalPrice?: number;
  rating: number;
  reviews: number;
  badge?: 'New' | 'Sale' | 'Hot';
  specs: string[];
  description: string;
  icon: string;       // SVG path 'd' attribute
  wishlisted: boolean;
  inStock: boolean;
}

export const MOCK_PRODUCTS: Product[] = [
  {
    id: 1, name: 'ProBook X1 Ultra', brand: 'ASUS', category: 'laptop',
    price: 1299.99, originalPrice: 1599.99, rating: 5, reviews: 248, badge: 'Sale',
    specs: ['Intel i9-14900H', '32GB DDR5', '1TB NVMe SSD', 'RTX 4070'],
    description: 'The ProBook X1 Ultra redefines mobile workstation performance. Engineered for creators and engineers who demand uncompromising power in a sleek chassis.',
    icon: 'M9 17.25v1.007a3 3 0 01-.879 2.122L7.5 21h9l-.621-.621A3 3 0 0115 18.257V17.25m6-12V15a2.25 2.25 0 01-2.25 2.25H5.25A2.25 2.25 0 013 15V5.25A2.25 2.25 0 015.25 3h13.5A2.25 2.25 0 0121 5.25z',
    wishlisted: false, inStock: true,
  },
  {
    id: 2, name: 'Galaxy S25 Edge', brand: 'Samsung', category: 'smartphone',
    price: 999.00, rating: 5, reviews: 512, badge: 'New',
    specs: ['6.7" Dynamic AMOLED', '200MP Sensor', 'Snapdragon 8 Gen 4', '5000mAh'],
    description: 'Pushing the boundaries of mobile photography and performance. The Galaxy S25 Edge features a breakthrough 200MP camera system and all-day battery life.',
    icon: 'M10.5 1.5H8.25A2.25 2.25 0 006 3.75v16.5a2.25 2.25 0 002.25 2.25h7.5A2.25 2.25 0 0018 20.25V3.75a2.25 2.25 0 00-2.25-2.25H13.5m-3 0V3h3V1.5m-3 0h3m-3 15h3',
    wishlisted: false, inStock: true,
  },
  {
    id: 3, name: 'SoundPeak Pro ANC', brand: 'Sony', category: 'audio',
    price: 349.00, originalPrice: 429.00, rating: 4, reviews: 891, badge: 'Hot',
    specs: ['40hr Battery', 'Active Noise Cancellation', 'Hi-Res Audio', 'LDAC'],
    description: 'Industry-leading noise cancellation meets audiophile-grade sound. The SoundPeak Pro is the reference standard for wireless listening.',
    icon: 'M9 9a3 3 0 114.5 2.598M12 2.25c-5.385 0-9.75 4.365-9.75 9.75s4.365 9.75 9.75 9.75 9.75-4.365 9.75-9.75S17.385 2.25 12 2.25z',
    wishlisted: false, inStock: true,
  },
  {
    id: 4, name: 'PadPro 12.9" M3', brand: 'Apple', category: 'tablet',
    price: 1099.00, rating: 5, reviews: 376, badge: 'New',
    specs: ['M3 Chip', '12.9" Liquid XDR', 'WiFi 6E', '16GB Unified Memory'],
    description: 'The most advanced iPad ever. With the M3 chip and Liquid XDR display, PadPro is a portable powerhouse that replaces your laptop.',
    icon: 'M10.5 19.5h3m-6.75 2.25h10.5a2.25 2.25 0 002.25-2.25v-15a2.25 2.25 0 00-2.25-2.25H6.75A2.25 2.25 0 004.5 4.5v15a2.25 2.25 0 002.25 2.25z',
    wishlisted: false, inStock: true,
  },
  {
    id: 5, name: 'ChronoFit Ultra 2', brand: 'Garmin', category: 'accessory',
    price: 449.00, originalPrice: 499.00, rating: 4, reviews: 203, badge: 'Sale',
    specs: ['Multi-band GPS', 'Heart Rate + SpO2', '7-day Battery', 'Sapphire Glass'],
    description: 'Built for athletes who demand precision. The ChronoFit Ultra 2 delivers military-grade durability with clinical-level health monitoring.',
    icon: 'M12 6v6h4.5m4.5 0a9 9 0 11-18 0 9 9 0 0118 0z',
    wishlisted: false, inStock: true,
  },
  {
    id: 6, name: 'Alpha FX 7R V', brand: 'Sony', category: 'accessory',
    price: 3499.00, rating: 5, reviews: 142,
    specs: ['61MP BSI CMOS', '4K 120fps', 'Dual CFexpress', '8-stop IBIS'],
    description: 'The pinnacle of full-frame mirrorless photography. Trusted by professionals worldwide, the Alpha FX 7R V delivers images that transcend expectations.',
    icon: 'M6.827 6.175A2.31 2.31 0 015.186 7.23c-.38.054-.757.112-1.134.175C2.999 7.58 2.25 8.507 2.25 9.574V18a2.25 2.25 0 002.25 2.25h15A2.25 2.25 0 0021.75 18V9.574c0-1.067-.75-1.994-1.802-2.169a47.865 47.865 0 00-1.134-.175 2.31 2.31 0 01-1.64-1.055l-.822-1.316a2.192 2.192 0 00-1.736-1.039 48.774 48.774 0 00-5.232 0 2.192 2.192 0 00-1.736 1.039l-.821 1.316z M16.5 12.75a4.5 4.5 0 11-9 0 4.5 4.5 0 019 0z',
    wishlisted: false, inStock: false,
  },
  {
    id: 7, name: 'Zenbook Pro Duo OLED', brand: 'ASUS', category: 'laptop',
    price: 1849.00, originalPrice: 2099.00, rating: 4, reviews: 187, badge: 'Sale',
    specs: ['Dual 4K OLED', 'RTX 4070', '64GB RAM', 'Intel i9'],
    description: 'Two screens. Infinite possibilities. The Zenbook Pro Duo redefines multitasking with its revolutionary dual OLED ScreenPad Plus.',
    icon: 'M9 17.25v1.007a3 3 0 01-.879 2.122L7.5 21h9l-.621-.621A3 3 0 0115 18.257V17.25m6-12V15a2.25 2.25 0 01-2.25 2.25H5.25A2.25 2.25 0 013 15V5.25A2.25 2.25 0 015.25 3h13.5A2.25 2.25 0 0121 5.25z',
    wishlisted: false, inStock: true,
  },
  {
    id: 8, name: 'Pixel 9 Pro XL', brand: 'Google', category: 'smartphone',
    price: 1099.00, rating: 5, reviews: 634, badge: 'New',
    specs: ['6.8" LTPO OLED', 'Tensor G4', '50MP Triple Camera', '7yr Updates'],
    description: 'Google\'s most intelligent phone ever. Tensor G4 powers AI features that learn and adapt to you, while the camera system captures moments in stunning detail.',
    icon: 'M10.5 1.5H8.25A2.25 2.25 0 006 3.75v16.5a2.25 2.25 0 002.25 2.25h7.5A2.25 2.25 0 0018 20.25V3.75a2.25 2.25 0 00-2.25-2.25H13.5m-3 0V3h3V1.5m-3 0h3m-3 15h3',
    wishlisted: false, inStock: true,
  },
  {
    id: 9, name: 'AirPods Max 2', brand: 'Apple', category: 'audio',
    price: 549.00, rating: 5, reviews: 1024, badge: 'Hot',
    specs: ['H2 Chip', 'Adaptive ANC', '30hr Battery', 'Spatial Audio'],
    description: 'Premium over-ear headphones reimagined. The H2 chip delivers computational audio that adapts in real time to your environment and ear cushion fit.',
    icon: 'M9 9a3 3 0 114.5 2.598M12 2.25c-5.385 0-9.75 4.365-9.75 9.75s4.365 9.75 9.75 9.75 9.75-4.365 9.75-9.75S17.385 2.25 12 2.25z',
    wishlisted: false, inStock: true,
  },
];