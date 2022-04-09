export interface NavItem {
  icon?: string;
  title: string;
  link: string;
  condition?: () => boolean;
}
