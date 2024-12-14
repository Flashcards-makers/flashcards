export interface FlashcardsEntity {
  id: number;
  name: string;
  description: string;
  icon: string;
  isPublic: boolean;
  createdBy: number;
  createdAt: string;
  pages: PagesEntity[];
}

export interface PagesEntity {}
