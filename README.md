# ProdAdminTools
<p>Conversion and updating tools for UK Product Admin</p>

<p>The repository contains three tools (couple of tools must have been removed since they were using sensitive information).
All files relating to product or price have been modified or removed to hide or delete sensitive information.</p><br>

<p><b>FJ updating price tool</b><br>
Reads a pricing file and splits product line codes to an individual template codes e.g 12345/6/50; 54351/4 to 12345, 12346, 12350 etc.
Converts template codes to full product codes e.g 12345M, 54321080M, 12350-29-30
Compares the product codes with database price list and updates prices.
Produces report showing which products and prices have been updated</p><br>


<p><b>Titleist updating price tool</b> - (this tool will not work as I had to remove price map containing sensitive information)<br>
Reads a pricing file and compares codes with the price map containing price keys.
Compares the product codes with database price list and updates prices.
Produces report showing which products and prices have been updated.</p><br>


<p><b>Master File upload tool</b><br>
Reads a master product file and adds required sizes and product structures.
converts the file it to match Master Product Load file template.</p>
