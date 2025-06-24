{
	description = "Development flake for Java 21";

	inputs = {
		nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
	};

	outputs = { self, nixpkgs }:
	let
		system = "x86_64-linux";
		pkgs = nixpkgs.legacyPackages.${system};
	in
	{
		devShells.${system}.default =
			pkgs.mkShell
			{
				buildInputs = with pkgs; [
					jdk21_headless
						maven
						bashInteractive
				];

				shellHook = ''
					export SHELL="/run/current-system/sw/bin/bash"
					 # Load secrets from environment or .env file
					if [ -f .env ]; then
						set -a
						source .env
						set +a
					fi
					'';
			};
	};
}
