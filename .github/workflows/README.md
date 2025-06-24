# GitHub Actions Setup

This directory contains GitHub Actions workflows for automated package publishing.

## Publish Workflow

The `publish.yml` workflow allows you to manually publish packages using Lerna with optional dry-run mode.

### Required Secrets

To use this workflow, you need to set up the following secrets in your GitHub repository:

#### 1. NPM_TOKEN

This token is required to publish packages to npm.

**Steps to create:**
1. Go to [npmjs.com](https://www.npmjs.com) and log in
2. Click on your profile picture → "Access Tokens"
3. Click "Generate New Token" → "Automation"
4. Copy the generated token
5. In your GitHub repository, go to Settings → Secrets and variables → Actions
6. Click "New repository secret"
7. Name: `NPM_TOKEN`
8. Value: Paste the npm token

#### 2. GITHUB_TOKEN (Automatic)

The `GITHUB_TOKEN` is automatically provided by GitHub Actions and doesn't need manual setup. It's used for:
- Checking out code
- Pushing version changes and tags
- Creating GitHub releases

### Workflow Features

- **Manual triggering**: Run the workflow manually from the GitHub Actions tab
- **Dry-run mode**: Test the workflow without actually publishing packages
- **Change detection**: Only publishes if there are actual changes to packages
- **Conventional commits**: Uses conventional commit messages for versioning
- **Independent versioning**: Each package can have its own version (as configured in lerna.json)
- **Build process**: Runs build scripts for all packages before publishing
- **GitHub releases**: Creates GitHub releases for published versions
- **Skip CI**: Prevents infinite loops by adding `[skip ci]` to release commits

### How to Use

1. **Navigate to Actions tab** in your GitHub repository
2. **Select "Publish Packages"** workflow from the left sidebar
3. **Click "Run workflow"** button
4. **Choose options**:
   - **Branch**: Select the branch to run from (usually main)
   - **Dry run**: Check this box to test without actually publishing
5. **Click "Run workflow"** to start the process

### Workflow Steps

1. **Checkout**: Fetches the repository code with full history
2. **Setup Node.js**: Installs Node.js 18 and configures npm registry
3. **Install dependencies**: Runs `npm ci` to install dependencies
4. **Build packages**: Runs build scripts for all packages
5. **Check for changes**: Determines if any packages have changes and lists them
6. **Version packages**: Updates package versions using conventional commits (or shows what would happen in dry-run)
7. **Publish packages**: Publishes changed packages to npm (or shows what would be published in dry-run)
8. **Push changes**: Commits and pushes version updates and tags (skipped in dry-run)
9. **Create release**: Creates a GitHub release (only for actual publishes)

### Customization

You can customize the workflow by:

- **Changing Node.js version**: Modify the `node-version` in the Setup Node.js step
- **Adding more build steps**: Add additional steps before the publish step
- **Modifying test behavior**: Remove `continue-on-error: true` to fail on test errors
- **Changing release format**: Modify the GitHub release creation step

### Troubleshooting

**Common issues:**

1. **NPM_TOKEN not working**: Ensure the token has "Automation" permissions
2. **Permission denied**: Check that the npm token belongs to a user/org with publish rights
3. **Git push fails**: Ensure the repository allows GitHub Actions to push
4. **No packages published**: Check if there are actual changes since the last release

**Debugging:**

- Check the Actions tab in your GitHub repository for detailed logs
- Verify that your packages have the correct `publishConfig` in their package.json
- Ensure your lerna.json configuration matches your needs
