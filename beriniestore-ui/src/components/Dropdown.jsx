export default function Dropdown({
  label,
  options,
  selectedValue,
  handleSort,
}) {
  return (
    <div className="flex items-center gap-2 justify-end pr-12 flex-1 font-primary">
      <label className="text-lg font-semibold text-primary dark:text-light">
        {label}
      </label>
      <select
        className="px-3 py-2 text-base border rounded-md transition border-primary dark:border-light focus:ring focus:ring-dark  dark:focus:ring-lighter focus:outline-none text-gray-800 dark:text-lighter"
        value={selectedValue}
        onChange={(e) => handleSort(e.target.value)}
      >
        {options.map((option, index) => (
          <option key={index} value={option}>
            {option}
          </option>
        ))}
      </select>
    </div>
  );
}
