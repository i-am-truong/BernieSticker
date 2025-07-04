import PageHeading from "./PageHeading";
import ProductListings from "./ProductListings";
import products from "../data/product";

export default function Home() {
  return (
    <div className="home-container">
      <PageHeading title={"Explore Bernie Stickers!"} />
      Add a touch of creativity to your space with our wide range of fun and
      unique stickers. Perfect for any occasion!
      <ProductListings products={products} />
    </div>
  );
}
