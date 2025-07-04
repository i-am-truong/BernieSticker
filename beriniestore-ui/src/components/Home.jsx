import PageHeading from "./PageHeading";
import ProductListings from "./ProductListings";
import products from "../data/product";

export default function Home() {
  return (
    <>
      <div className="max-w-[1152px] mx-auto px-6 py-8">
        <PageHeading title="Explore Bernie Stickers!">
          Add a touch of creativity to your space with our wide range of fun and
          unique stickers. Perfect for any occasion!
        </PageHeading>
        <ProductListings products={products} />
      </div>
    </>
  );
}
